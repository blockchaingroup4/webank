package org.fisco.bcos.clients;


import lombok.Data;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;

@Data
public abstract class ContractClient {
    Credentials credentials;
    String contractAddress;
    Web3j web3j;
    public ContractClient(Credentials credentials, String contractAddress, Web3j web3j){
        this.credentials = credentials;
        this.setContractAddress(contractAddress);
        this.setWeb3j(web3j);
        load();
    }

    public abstract void load();
}
