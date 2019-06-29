package org.fisco.bcos.clients;

import org.fisco.bcos.web3j.crypto.Credentials;

public class TransactionContractClient extends ContractClient{
    public TransactionContractClient(Credentials credentials){
        super(credentials);
        load();
    }

    public void load(){

    }
}
