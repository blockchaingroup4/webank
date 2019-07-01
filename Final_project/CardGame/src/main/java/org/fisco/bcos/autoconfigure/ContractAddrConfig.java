package org.fisco.bcos.autoconfigure;

import lombok.Data;
import org.fisco.bcos.beans.ContractAddr;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "contract-addr")
public class ContractAddrConfig {
    private String accountContractAddress;
    private String cardContractAddress;
    private String marketContractAddress;
    private String reverseContractAddress;
    private String transactionContractAddress;
    @Bean
    public ContractAddr getContractAddr(){
        ContractAddr contractAddr = new ContractAddr();
        contractAddr.setAccountContractAddress(accountContractAddress);
        contractAddr.setCardContractAddress(cardContractAddress);
        contractAddr.setMarketContractAddress(marketContractAddress);
        contractAddr.setReverseContractAddress(reverseContractAddress);
        contractAddr.setTransactionContractAddress(transactionContractAddress);
        return contractAddr;
    }
}
