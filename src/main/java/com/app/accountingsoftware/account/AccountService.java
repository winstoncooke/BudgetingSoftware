package com.app.accountingsoftware.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(long accountNumber) {
        checkAccountExists(accountNumber);
        return accountRepository.getById(accountNumber);
    }

    public void addAccount(Account account) {
        checkAccountNameIsNotTaken(account);
        accountRepository.save(account);
    }

    public void deleteAccount(long accountNumber) {
        checkAccountExists(accountNumber);

        // Check that the account does not carry a balance before deleting it, so that books remain balanced
        checkAccountBalanceIsZero(accountNumber);
        accountRepository.deleteById(accountNumber);
    }

    @Transactional
    public void doubleEntry(long firstAccountNumber, long secondAccountNumber, double amount) {
        checkAccountExists(firstAccountNumber);
        checkAccountExists(secondAccountNumber);
        updateBalance(firstAccountNumber, amount);
        updateBalance(secondAccountNumber, amount);
    }

    @Transactional
    public void updateBalance(long accountNumber, double amount) {
        checkAccountExists(accountNumber);  // Remove when doubleEntry is implemented to eliminate redundancy
        Account account = accountRepository.getById(accountNumber);
        account.updateBalance(amount);
    }

    private void checkAccountExists(long accountNumber) {
        boolean exists = accountRepository.existsById(accountNumber);
        if (!exists) {
            throw new IllegalStateException("Account " + accountNumber + " does not exist");
        }
    }

    private void checkAccountNameIsNotTaken(Account account) {
        Optional<Account> accountOptional = accountRepository.findAccountByName(account.getName());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("Account name already taken");
        }
    }

    private void checkAccountBalanceIsZero(long accountNumber) {
        Account account = accountRepository.getById(accountNumber);
        double balance = account.getBalance();
        if (balance > 0) {
            throw new IllegalStateException("Account balance must be 0 before deleting the account");
        }
    }
}
