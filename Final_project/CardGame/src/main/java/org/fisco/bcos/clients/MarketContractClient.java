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
            contract.createCardAndGiveTo(info.getName(), credentials.getAddress(), info.getCardId(), info.getUrl(), BigInteger.valueOf(info.getLevel())).send();
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public Integer recharge(String amount){
        return 0;
    }
}
