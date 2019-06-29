package org.fisco.bcos.clients;


import org.fisco.bcos.web3j.crypto.Credentials;

public class ContractClient {
    Credentials credentials;
    public static final String ACCOUNT_CONTRACT_ADDRESS = "";
    public static final String MARKET_CONTRACT_ADDRESS = "";
    public static final String CARD_CONTRACT_ADDRESS = "";
    public static final String TRANSACTION_CONTRACT_ADDRESS = "";
    public ContractClient(Credentials credentials){
        this.credentials = credentials;
    }

}
