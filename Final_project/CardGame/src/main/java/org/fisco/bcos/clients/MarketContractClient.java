package org.fisco.bcos.clients;

import org.fisco.bcos.beans.CardInfo;
import org.fisco.bcos.web3j.crypto.Credentials;

import java.util.Arrays;
import java.util.List;

public class MarketContractClient extends ContractClient{
    public MarketContractClient(Credentials credentials){
        super(credentials);
        load();
    }

    public void load(){

    }

    public List<String>getCardsOnSale(){
        return Arrays.asList("xxxxx", "xxxxx", "yyyyy");
    }

    public Integer buyCard(String cardId){
        return 0;
    }

    public Integer pullCard(String cardId){
        return 0;
    }

    public Integer pushCard(String cardId){
        return 0;
    }

    public Integer setCardPrice(String cardId, String price){
        return 0;
    }

    public CardInfo drawCard(){
        CardInfo info = new CardInfo();
        info.setUrl(".....");
        return info;
    }

    public Integer recharge(String amount){
        return 0;
    }
}
