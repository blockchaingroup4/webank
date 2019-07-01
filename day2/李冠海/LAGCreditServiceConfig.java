package org.fisco.bcos.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.LAGCreditService;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LAGCreditServiceConfig {
    @Bean
    public LAGCreditService getLAGCreditService(Web3j web3j, Credentials credentials){
        return new LAGCreditService(web3j, credentials);
    }
}
