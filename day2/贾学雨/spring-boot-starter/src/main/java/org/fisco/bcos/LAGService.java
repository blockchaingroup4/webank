package org.fisco.bcos;

import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contract.LAG;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;


import java.math.BigInteger;

@Slf4j
public class LAGService {
    Web3j web3j;
    Credentials credentials;
    public LAGService(Web3j web3j, Credentials credentials){
        this.web3j = web3j;
        this.credentials = credentials;
    }
    public LAG deploy(){
        log.info("web3j : {}", web3j);
        LAG lag = null;
        try{
            lag = LAG.deploy(web3j, credentials,
                    new StaticGasProvider(GasConstants.GAS_PRICE,GasConstants.GAS_LIMIT),
                    new BigInteger("100000"), "LAGC", "LAG").send();
            log.info("LAGC address is {}", lag.getContractAddress());
            return lag;
        }catch (Exception e){
            log.error("deploy lacg contract fail: {}", e.getMessage());
        }
        return lag;
    }

    public LAG load(String creditAddress){
        LAG lag = LAG.load(creditAddress, web3j, credentials,
                new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        return lag;
    }

    public boolean transfer(String creditAddress, String to, BigInteger value){
        try{
            LAG lag = load(creditAddress);
            TransactionReceipt receipt = lag.transfer(to, value).send();
            log.info("status : {}", receipt.getStatus());
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public long getBalanceByOwner(String creditAddress, String owner)throws Exception{
        LAG lag = load(creditAddress);
        BigInteger balance = lag.balanceOf(owner).send();
        return balance.longValue();
    }

    public long getTotalSupply(String creditAddress) throws Exception{
        LAG lag = load(creditAddress);
        BigInteger total = lag.getTotalSupply().send();
        return total.longValue();
    }
}
