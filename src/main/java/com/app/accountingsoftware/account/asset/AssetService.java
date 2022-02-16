package com.app.accountingsoftware.account.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAccounts() {
        return assetRepository.findAll();
    }

    public Asset getAccountById(long accountNumber) {
        checkAccountExists(accountNumber);
        return assetRepository.getById(accountNumber);
    }

    public void addAccount(Asset account) {
        checkAccountNameIsNotTaken(account);
        assetRepository.save(account);
    }

    public void deleteAccount(long accountNumber) {
        checkAccountExists(accountNumber);

        // Check that the account does not carry a balance before deleting it, so that books remain balanced
        checkAccountBalanceIsZero(accountNumber);
        assetRepository.deleteById(accountNumber);
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
        Asset account = assetRepository.getById(accountNumber);
        account.updateBalance(amount);
    }

    private void checkAccountExists(long accountNumber) {
        boolean exists = assetRepository.existsById(accountNumber);
        if (!exists) {
            throw new IllegalStateException("Account " + accountNumber + " does not exist");
        }
    }

    private void checkAccountNameIsNotTaken(Asset account) {
        Optional<Asset> accountOptional = assetRepository.findAccountByName(account.getName());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("Account name already taken");
        }
    }

    private void checkAccountBalanceIsZero(long accountNumber) {
        Asset account = assetRepository.getById(accountNumber);
        double balance = account.getBalance();
        if (balance > 0) {
            throw new IllegalStateException("Account balance must be 0 before deleting the account");
        }
    }
}
