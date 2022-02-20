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

        double firstUpdateAmount = amount;
        double secondUpdateAmount = amount;

        // Determine if the accounts are being debited or credited based on account type
        // Debit side of the entry
        if (!accountRepository.getById(firstAccountNumber).getType().equals("Asset") &&
                !accountRepository.getById(firstAccountNumber).getType().equals("Expense")) {
            firstUpdateAmount = amount * -1;
        }

        // Credit side of the entry
        if (accountRepository.getById(secondAccountNumber).getType().equals("Asset") ||
                accountRepository.getById(secondAccountNumber).getType().equals("Expense")) {
            secondUpdateAmount = amount * -1;
        }

        updateBalance(firstAccountNumber, firstUpdateAmount);
        updateBalance(secondAccountNumber, secondUpdateAmount);
    }

    @Transactional
    private void updateBalance(long accountNumber, double amount) {
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
