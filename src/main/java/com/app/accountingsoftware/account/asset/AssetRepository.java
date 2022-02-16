package com.app.accountingsoftware.account.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    // SELECT * FROM asset WHERE name = ?
    Optional<Asset> findAccountByName(String name);
}
