package com.app.accountingsoftware.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping(path = "{type}")
    public List<Account> getAccountsByType(@PathVariable String type) {
        return accountService.getAccountsByType(type);
    }

    @PostMapping
    public void createAccount(@RequestBody Account account) {
        accountService.addAccount(account);
    }

    @DeleteMapping(path = "{accountNumber}")
    public void deleteAccount(@PathVariable("accountNumber") long accountNumber) {
        accountService.deleteAccount(accountNumber);
    }

    @PutMapping(path = "{firstAccountNumber}/{secondAccountNumber}")
    public void doubleEntry(@PathVariable("firstAccountNumber") long firstAccountNumber,
                             @PathVariable("secondAccountNumber") long secondAccountNumber,
                             double amount) {
        accountService.doubleEntry(firstAccountNumber, secondAccountNumber, amount);
    }
}