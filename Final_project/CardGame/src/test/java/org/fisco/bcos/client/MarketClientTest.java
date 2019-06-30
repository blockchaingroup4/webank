package org.fisco.bcos.client;

import org.fisco.bcos.BaseTest;
import org.fisco.bcos.beans.AccountInfo;
import org.fisco.bcos.beans.CardInfo;
import org.fisco.bcos.beans.ContractAddr;
import org.fisco.bcos.clients.AccountContractClient;
import org.fisco.bcos.clients.MarketContractClient;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contracts.MarketContract;
import org.fisco.bcos.util.KeyUtil;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.txdecode.ResultEntity;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoderFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

public class MarketClientTest extends BaseTest {
    @Autowired
    Web3j web3j;
    @Autowired
    Credentials credentials;
    @Autowired
    ContractAddr contractAddr;
    @Ignore
    @Test
    public void testDrawCard(){
        MarketContract contract = MarketContract.load(contractAddr.getMarketContractAddress(), web3j, credentials, GasConstants.STATIC_GAS_PROVIDER);
        try {
            TransactionReceipt receipt = contract.drawCard(credentials.getAddress(), "haha").send();
            List<MarketContract.DrawCardEventEventResponse> responses = contract.getDrawCardEventEvents(receipt);
            if(!responses.isEmpty()){
                BigInteger level = responses.get(0).level;
                BigInteger cardId = responses.get(0).cardId;
                System.out.println("level: " + level + "\ncardId: " + cardId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDrawCard2(){
        System.out.println(KeyUtil.getAddress(credentials));
        MarketContractClient marketContractClient = new MarketContractClient(credentials, contractAddr.getMarketContractAddress(), web3j);
        AccountContractClient accountContractClient = new AccountContractClient(credentials, contractAddr.getAccountContractAddress(), web3j);
//        accountContractClient.addAccount("hello");
        try {
            System.out.println(accountContractClient.getAccountInfo());
//            System.out.println(accountContractClient.getContract().getDrawCountOf(credentials.getAddress()).send());
//            System.out.println(marketContractClient.drawCard("i wish"));
//            System.out.println(accountContractClient.getContract().getDrawCountOf(credentials.getAddress()).send());
//            System.out.println(accountContractClient.getAccountInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
