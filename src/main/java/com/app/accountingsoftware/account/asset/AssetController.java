package com.app.accountingsoftware.account.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/asset")
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<Asset> getAccounts() {
        return assetService.getAccounts();
    }

//    @GetMapping(path = "{accountNumber}")
//    public Asset getAccountById(@PathVariable("accountNumber") long accountNumber) {
//        return assetService.getAccountById(accountNumber);
//    }

    @PostMapping
    public void createAccount(@RequestBody Asset account) {
        assetService.addAccount(account);
    }

    @DeleteMapping(path = "{accountNumber}")
    public void deleteAccount(@PathVariable("accountNumber") long accountNumber) {
        assetService.deleteAccount(accountNumber);
    }

//    @PutMapping(path = "{firstAccountNumber}/{secondAccountNumber}")
//    public void doubleEntry(@PathVariable("firstAccountNumber") long firstAccountNumber,
//                             @PathVariable("secondAccountNumber") long secondAccountNumber,
//                             double amount) {
//        assetService.doubleEntry(firstAccountNumber, secondAccountNumber, amount);
//    }

    @PutMapping(path = "{accountNumber}")
    public void updateBalance(@PathVariable("accountNumber") long accountNumber,
                              @RequestParam double amount) {
        assetService.updateBalance(accountNumber, amount);
    }
}
