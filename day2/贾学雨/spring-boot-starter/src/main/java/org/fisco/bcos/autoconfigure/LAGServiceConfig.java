package org.fisco.bcos.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.LAGService;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LAGServiceConfig {
    @Bean
    public LAGService getLAGService(Web3j web3j, Credentials credentials){
        return new LAGService(web3j, credentials);
    }
}
