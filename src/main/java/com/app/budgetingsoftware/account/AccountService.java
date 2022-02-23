package com.app.budgetingsoftware.account;

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

    public List<Account> getAccountsByCategory(String category) {
        return accountRepository.findAccountByCategory(category);
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
    public void addExpense(long accountNumber, double amount) {
        checkAccountExists(accountNumber);
        Account account = accountRepository.getById(accountNumber);

        // Check to see if expenses remain under budget before allowing the transaction
        checkUpdatedBalanceIsWithinBudget(account, amount);
        account.updateExpenses(amount);
    }

    private void checkAccountExists(long accountNumber) {
        boolean exists = accountRepository.existsById(accountNumber);
        if (!exists) {
            throw new IllegalStateException("Account " + accountNumber + " does not exist");
        }
    }

    private void checkUpdatedBalanceIsWithinBudget(Account account, double amount) {
        double budget = account.getBudget();
        double expenses = account.getExpenses();

        if (expenses + amount > budget) {
            throw new IllegalStateException("Total expenses exceed budgeted amount!");
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
        double balance = account.getExpenses();
        if (balance > 0) {
            throw new IllegalStateException("Account balance must be 0 before deleting the account!");
        }
    }
}
