package org.fisco.bcos.clients;

import org.fisco.bcos.beans.AccountInfo;
import org.fisco.bcos.web3j.crypto.Credentials;

import java.util.Arrays;

public class AccountContractClient extends ContractClient{
    public AccountContractClient(Credentials credentials){
        super(credentials);
        load();
    }

    public void load(){

    }

    public AccountInfo getAccountInfo(){
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setName("hello world");
        accountInfo.setBalance("99999");
        accountInfo.setDrawCount("5");
        accountInfo.setCardsId(Arrays.asList("123456", "456789"));
        accountInfo.setTransactionsId(Arrays.asList("123", "456"));
        accountInfo.setAddress(credentials.getAddress());
        return accountInfo;
    }

}
