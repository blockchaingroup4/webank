package org.fisco.bcos;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.contract.LAG;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

@Slf4j
public class LAGServiceTest extends BaseTest{
    @Autowired
    Web3j web3j;
    @Autowired
    Credentials credentials;
    @Autowired
    LAGService lagService;
    String creditAddr = "0xb7332cc0e30024551d9620a1b7e7cae5a23de1d4";
    String ownerAddr = "94e949260187b1a6ea9be7c513aa5791a74c6975";
    String toAddr = "c2428ce29bf854a93327dec483e26f5e64802e24";

    @Test
    public void testDeploy(){
        LAG lag = lagService.deploy();
        log.info("LAG address : {}", lag.getContractAddress());
    }

    @Test
    public void testLoad(){
        LAG lag = lagService.load(creditAddr);
        log.info("LAG address : {}", lag.getContractAddress());
    }

    @Test
    public void testTransfer(){
        boolean flag = lagService.transfer(creditAddr, toAddr, new BigInteger("1000"));
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
            long balance = lagService.getBalanceByOwner(creditAddr, ownerAddr);
            log.info("balance of owner : {}", balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTotalSupply(){
        try {
            long totalSupply = lagService.getTotalSupply(creditAddr);
            log.info("total supply : {}", totalSupply);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
