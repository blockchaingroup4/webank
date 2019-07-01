package org.fisco.bcos.client;

import org.fisco.bcos.BaseTest;
import org.fisco.bcos.beans.*;
import org.fisco.bcos.clients.*;
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
import java.security.Key;
import java.util.List;

public class MarketClientTest extends BaseTest {
    @Autowired
    Web3j web3j;
    @Autowired
    Credentials credentials;
    @Autowired
    ContractAddr contractAddr;
    @Autowired
    TestKey keys;
    @Ignore
    @Test
    public void testDrawCard(){
        MarketContract contract = MarketContract.load(contractAddr.getMarketContractAddress(), web3j, credentials, GasConstants.STATIC_GAS_PROVIDER);
        try {
            TransactionReceipt receipt = contract.drawCard(credentials.getAddress(), "haha").send();
            List<MarketContract.DrawCardEventEventResponse> responses = contract.getDrawCardEventEvents(receipt);
            if(!responses.isEmpty()){
                BigInteger level = responses.get(0).level;
                String cardId = responses.get(0).cardId;
                System.out.println("level: " + level + "\ncardId: " + cardId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void testDrawCard2(){
        System.out.println(KeyUtil.getAddress(credentials));
        MarketContractClient marketContractClient = new MarketContractClient(credentials, contractAddr.getMarketContractAddress(), web3j);
        AccountContractClient accountContractClient = new AccountContractClient(credentials, contractAddr.getAccountContractAddress(), web3j);
        accountContractClient.addAccount("hello");
        try {
            System.out.println(accountContractClient.getAccountInfo());
            System.out.println(accountContractClient.getContract().getDrawCountOf(credentials.getAddress()).send());
            System.out.println(marketContractClient.drawCard("i wish"));
            System.out.println(accountContractClient.getContract().getDrawCountOf(credentials.getAddress()).send());
            System.out.println(accountContractClient.getAccountInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void println(Object obj){
        System.out.println("###########################" + obj);
    }

//    @Ignore
    @Test
    public  void totalTest(){
        String key[] = {keys.getUser1(), keys.getUser2(), keys.getUser3()};
        String addresses[] = {KeyUtil.getAddress(key[0]), KeyUtil.getAddress(key[1]), KeyUtil.getAddress(key[2])};
        Credentials cred[] = {Credentials.create(key[0]), Credentials.create(key[1]), Credentials.create(key[2])};
        MarketContractClient marketClient1 = new MarketContractClient(cred[0], contractAddr.getMarketContractAddress(), web3j);
        MarketContractClient marketClient2 = new MarketContractClient(cred[1], contractAddr.getMarketContractAddress(), web3j);
        AccountContractClient accountClient1 = new AccountContractClient(cred[0], contractAddr.getAccountContractAddress(), web3j);
        AccountContractClient accountClient2 = new AccountContractClient(cred[1], contractAddr.getAccountContractAddress(), web3j);
        CardContractClient cardClient1 = new CardContractClient(cred[0], contractAddr.getCardContractAddress(),web3j);
        CardContractClient cardClient2 = new CardContractClient(cred[1], contractAddr.getCardContractAddress(), web3j);

        //添加用户
        accountClient1.addAccount("hello");
        accountClient2.addAccount("world");


        //获取信息
        AccountInfo accountInfo1 = accountClient1.getAccountInfo();
        AccountInfo accountInfo2 = accountClient2.getAccountInfo();
        //打印信息
        println(accountInfo1);
        println(accountInfo2);


        //用户1抽2次卡，用户2抽1次卡
        marketClient1.drawCard("hahha");
        marketClient1.drawCard("abcde");
        marketClient1.drawCard("...");
        marketClient2.drawCard("xxxxx");
        marketClient2.drawCard("iiixxx");
        accountInfo1 = accountClient1.getAccountInfo();
        accountInfo2 = accountClient2.getAccountInfo();
        assert(Integer.valueOf(accountInfo1.getDrawCount()) == 2);
        assert(Integer.valueOf(accountInfo2.getDrawCount()) == 3);
        println(accountInfo1);
        println(accountInfo2);
        List<String>cards1 = accountInfo1.getCardsId();
        List<String>cards2 = accountInfo2.getCardsId();
        assert(cards1.size() == 3);
        assert(cards2.size() == 2);
        CardInfo cardInfo1 = cardClient1.getCardInfo(cards1.get(0));
        CardInfo cardInfo2 = cardClient2.getCardInfo(cards2.get(0));
        println(cardInfo1);
        println(cardInfo2);
        marketClient1.pushCard(cards1.get(0), new BigInteger("10"));
        marketClient2.pushCard(cards2.get(0), new BigInteger("20"));
        cardInfo1 = cardClient1.getCardInfo(cards1.get(0));
        cardInfo2 = cardClient2.getCardInfo(cards2.get(0));
        println(cardInfo1);
        println(cardInfo2);
        assert(cardInfo1.isOnSale());
        assert(cardInfo2.isOnSale());

        List<String>cardsOnSale = marketClient1.getCardsOnSale();
        assert(cardsOnSale.size() > 0);
        println(cardsOnSale);
        marketClient1.pullCard(cards1.get(0));
        cardInfo1 = cardClient1.getCardInfo(cards1.get(0));;
        assert(!cardInfo1.isOnSale());
        println(cardInfo1);

        marketClient1.recharge("10000");
        marketClient2.recharge("10000");

        TransactionContractClient transClient1 = new TransactionContractClient(cred[0], contractAddr.getTransactionContractAddress(), web3j);
        TransactionContractClient transClient2 = new TransactionContractClient(cred[1],contractAddr.getTransactionContractAddress(), web3j);
        int oriSize = accountInfo1.getTransactionsId().size();
        println("before buy card");
        accountInfo1 = accountClient1.getAccountInfo();
        accountInfo2 = accountClient2.getAccountInfo();
        println(accountInfo1);
        println(accountInfo2);
        marketClient1.buyCard(cardInfo2.getCardId());
        println("after buy card");
        accountInfo1 = accountClient1.getAccountInfo();
        accountInfo2 = accountClient2.getAccountInfo();
        int newSize = accountInfo1.getTransactionsId().size();
        assert(newSize == oriSize + 1);
        assert(accountInfo1.getTransactionsId().size() == accountInfo2.getTransactionsId().size());
        println(accountInfo1);
        println(accountInfo2);
        cardInfo2 = cardClient2.getCardInfo(cardInfo2.getCardId());
        assert(!cardInfo2.isOnSale());

        TransactionInfo transInfo1 = transClient1.getTransactionInfo(accountInfo1.getTransactionsId().get(0));
        ReverseContractClient revClient1 = new ReverseContractClient(cred[0], contractAddr.getReverseContractAddress(), web3j);
        println("before reverse");
        println(transInfo1);
        List<ReverseInfo> revs = revClient1.getReverseApplies();
        oriSize = revs.size();
        revClient1.applyForReverse(transInfo1.getTransactionId(), "i just want to reverse");
        println("after reversing");
        revs = revClient1.getReverseApplies();
        newSize = revs.size();
        assert(newSize == oriSize + 1);
        println("orisize: "+oriSize);
        println("newsize: "+newSize);
        transInfo1 = transClient1.getTransactionInfo(accountInfo1.getTransactionsId().get(0));
        println(transInfo1);

        println("after reversed");

    }
}
