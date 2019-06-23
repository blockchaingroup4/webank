package org.fisco.bcos.autoconfigure;

import org.fisco.bcos.ShopClient;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopClientConfig {
    @Bean
    public ShopClient getShopClient(Web3j web3j, Credentials credentials) {
        return new ShopClient(web3j, credentials);
    }
}
