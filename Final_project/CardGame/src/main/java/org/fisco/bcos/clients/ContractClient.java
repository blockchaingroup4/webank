package org.fisco.bcos.clients;


import lombok.Data;
import org.fisco.bcos.web3j.crypto.Credentials;

@Data
public abstract class ContractClient {
    Credentials credentials;
    String contractAddress;
    public ContractClient(Credentials credentials, String contractAddress){
        this.credentials = credentials;
        this.setContractAddress(contractAddress);
        load();
    }

    public abstract void load();
}
