package com.app.budgetingsoftware.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping(path = "{category}")
    public List<Account> getAccountsByCategory(@PathVariable String category) {
        return accountService.getAccountsByCategory(category);
    }

    @PostMapping
    public void createAccount(@RequestBody Account account) {
        accountService.addAccount(account);
    }

    @DeleteMapping(path = "{accountNumber}")
    public void deleteAccount(@PathVariable("accountNumber") long accountNumber) {
        accountService.deleteAccount(accountNumber);
    }

    @PutMapping(path = "{accountId}")
    public void addExpense(@PathVariable("accountId") long accountId, double amount) {
        accountService.addExpense(accountId, amount);
    }
}
