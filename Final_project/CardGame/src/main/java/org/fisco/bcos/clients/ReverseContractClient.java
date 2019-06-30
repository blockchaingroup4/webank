package org.fisco.bcos.clients;


import org.fisco.bcos.beans.ReverseInfo;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;

import java.util.Arrays;
import java.util.List;

public class ReverseContractClient extends ContractClient{
    public static String ADDRESS="...";
    public ReverseContractClient(Credentials credentials, String contractAddress, Web3j web3j){
        super(credentials, contractAddress, web3j);
    }

    @Override
    public void load(){

    }

    public Integer applyForReverse(String transactionId, String reason){
        return 0;
    }

    public List<String> getReverseApplies(){
        return Arrays.asList("xxx", "yyy", "zzz");
    }

    public ReverseInfo getReverseInfo(String reverseId){
        return new ReverseInfo();
    }

    public Integer sendReverse(String reverseId){
        return 0;
    }

    public Integer setReverseResult(String reverseId, Boolean result){
        return 0;
    }
}
