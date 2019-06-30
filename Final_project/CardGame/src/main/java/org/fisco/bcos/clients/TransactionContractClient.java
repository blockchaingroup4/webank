package org.fisco.bcos.clients;

import org.fisco.bcos.beans.TransactionInfo;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;

public class TransactionContractClient extends ContractClient{
    public TransactionContractClient(Credentials credentials, String contractAddress, Web3j web3j){
        super(credentials, contractAddress, web3j);
    }

    @Override
    public void load(){

    }

    public TransactionInfo getTransactionInfo(String transactionId){
        return new TransactionInfo();
    }
}
