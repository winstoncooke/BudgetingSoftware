package com.app.accountingsoftware.account;

import com.app.accountingsoftware.account.asset.Asset;
import com.app.accountingsoftware.account.liability.Liability;
import com.app.accountingsoftware.account.equity.Equity;

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

//    @PutMapping(path = "{accountNumber}")
//    public void updateBalance(@PathVariable("accountNumber") long accountNumber,
//                              @RequestParam double amount) {
//        accountService.updateBalance(accountNumber, amount);
//    }
}
