package org.fisco.bcos.clients;

import lombok.Data;
import org.fisco.bcos.beans.CardInfo;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contracts.MarketContract;
import org.fisco.bcos.util.CardDB;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class MarketContractClient extends ContractClient{
    MarketContract contract;
    public MarketContractClient(Credentials credentials, String contractAddress, Web3j web3j){
        super(credentials, contractAddress, web3j);
    }

    @Override
    public void load(){
        contract = MarketContract.load(getContractAddress(), getWeb3j(), getCredentials(), GasConstants.STATIC_GAS_PROVIDER);
    }

    //todo
    public List<String>getCardsOnSale(){
        List<String> ret = new ArrayList<>();
        try {
            int num = contract.getCardsOnSaleNum().send().intValue();
            for(int i = 0; i < num; i++){
                ret.add(contract.getAddressOfCardOnSale(BigInteger.valueOf(i)).send());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    public Integer buyCard(String cardId){
        try {
            contract.buyCard(credentials.getAddress(), cardId).send();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public Integer pullCard(String cardId){
        try {
            contract.pullCard(cardId).send();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public Integer pushCard(String cardId, BigInteger amount){
        try {
            contract.pushCard(cardId, amount).send();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }



    public CardInfo drawCard(String wish){
        CardInfo info = new CardInfo();
        try {
            TransactionReceipt receipt = contract.drawCard(credentials.getAddress(), wish).send();
            List<MarketContract.DrawCardEventEventResponse>responses = contract.getDrawCardEventEvents(receipt);
            if(!responses.isEmpty()){
                info.setLevel(responses.get(0).level.intValue());
                info.setCardId(responses.get(0).cardId);
            }
            CardDB.setCardUrlAndName(info);
            receipt = contract.createCardAndGiveTo(info.getName(), credentials.getAddress(), info.getCardId(), info.getUrl(), BigInteger.valueOf(info.getLevel())).send();
            List<MarketContract.CreateCardAndGiveEventEventResponse>responses1 = contract.getCreateCardAndGiveEventEvents(receipt);
            if(!responses1.isEmpty()){
                info.setPrice(String.valueOf(responses1.get(0).price));
                info.setOnSale(responses1.get(0).isOnSale);
                info.setOwner(responses1.get(0).owner);
            }
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public Integer recharge(String amount){
        try {
            contract.recharge(credentials.getAddress(), new BigInteger(amount)).send();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    //todo
    public Integer buyDrawCard(String times){
        try {
            contract.buyDrawCards(credentials.getAddress(), new BigInteger(times)).send();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}
