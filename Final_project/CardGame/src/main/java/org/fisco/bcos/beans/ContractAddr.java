package org.fisco.bcos.beans;

import lombok.Data;

@Data
public class ContractAddr {
    private String accountContractAddress;
    private String cardContractAddress;
    private String marketContractAddress;
    private String reverseContractAddress;
    private String transactionContractAddress;
}
