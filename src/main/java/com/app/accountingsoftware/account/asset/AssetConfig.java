package com.app.accountingsoftware.account.asset;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AssetConfig {

    @Bean
    CommandLineRunner assetCommandLineRunner(AssetRepository repository) {
        return args -> {
//            Asset cash = new Asset("Cash");
//            repository.saveAll(List.of(cash));
        };
    }
}
