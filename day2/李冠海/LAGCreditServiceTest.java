package org.fisco.bcos;

import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.contract.LAGCredit;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

@Slf4j
public class LAGCreditServiceTest extends BaseTest{
    @Autowired
    Web3j web3j;
    @Autowired
    Credentials credentials;
    @Autowired
    LAGCreditService lagCreditService;
    String creditAddr = "4eaa1c373378cc32bdbeb5fcf8c039af4a0924fd";
    String ownerAddr = "1efe452bf2f0dca9acacac03e5e94e33f76535dd";
    String toAddr = "9cea183631c358a04dd519abb90c7a45ed371612";

    @Test
    public void testDeploy(){
        LAGCredit lagCredit = lagCreditService.deploy();
        log.info("LAGCredit address : {}", lagCredit.getContractAddress());
    }

    @Test
    public void testLoad(){
        LAGCredit lagCredit = lagCreditService.load(creditAddr);
        log.info("LAGCredit address : {}", lagCredit.getContractAddress());
    }

    @Test
    public void testTransfer(){
        boolean flag = lagCreditService.transfer(creditAddr, toAddr, new BigInteger("1000"));
        if(flag){
            log.info("transfer success!");
        }
        else{
            log.info("transfer failed!");
        }
    }

    @Test
    public void testGetBalanceByOwner(){
        try {
            long balance = lagCreditService.getBalanceByOwner(creditAddr, ownerAddr);
            log.info("balance of owner : {}", balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTotalSupply(){
        try {
            long totalSupply = lagCreditService.getTotalSupply(creditAddr);
            log.info("total supply : {}", totalSupply);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
