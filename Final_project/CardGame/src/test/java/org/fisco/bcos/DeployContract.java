package org.fisco.bcos;


import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contracts.*;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DeployContract extends BaseTest{
    @Autowired
    Web3j web3j;
    @Autowired
    Credentials credentials;
//    @Ignore
    @Test
    public void deployAllContract(){
        try {
            AccountManagementContract accountContract = AccountManagementContract.deploy(web3j, credentials, GasConstants.STATIC_GAS_PROVIDER).send();
            CardManagementContract cardContract = CardManagementContract.deploy(web3j, credentials, GasConstants.STATIC_GAS_PROVIDER).send();
            TransactionManagementContract transactionContract = TransactionManagementContract.deploy(web3j, credentials, GasConstants.STATIC_GAS_PROVIDER).send();
            MarketContract marketContract = MarketContract.deploy(web3j, credentials, GasConstants.STATIC_GAS_PROVIDER).send();
            ReverseManagementContract reverseContract = ReverseManagementContract.deploy(web3j, credentials, GasConstants.STATIC_GAS_PROVIDER).send();
            String accountAddr = accountContract.getContractAddress();
            String cardAddr = cardContract.getContractAddress();
            String transAddr = transactionContract.getContractAddress();
            String marketAddr = marketContract.getContractAddress();
            String reverseAddr = reverseContract.getContractAddress();
            marketContract.setACTInterfaces(accountAddr, cardAddr, transAddr).send();
            reverseContract.setACTInterfaces(accountAddr, cardAddr,  transAddr).send();
            System.out.println("  account-contract-address: \""+accountAddr+"\"\n" +
                    "  card-contract-address: \"" + cardAddr+"\"\n" +
                    "  transaction-contract-address: \""+transAddr+"\"\n" +
                    "  market-contract-address: \""+marketAddr+"\"\n" +
                    "  reverse-contract-address: \""+reverseAddr+"\"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
