package org.fisco.bcos.clients;

import lombok.Data;
import org.fisco.bcos.beans.CardInfo;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contracts.CardManagementContract;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;

import java.math.BigInteger;

@Data
public class CardContractClient extends ContractClient{
    CardManagementContract contract;
    public CardContractClient(Credentials credentials, String contractAddress, Web3j web3j){
        super(credentials, contractAddress, web3j);
    }

    @Override
    public void load(){
        contract = CardManagementContract.load(credentials.getAddress(), getWeb3j(), getCredentials(), GasConstants.STATIC_GAS_PROVIDER);
    }

    public CardInfo getCardInfo(String cardId){
        CardInfo cardInfo = new CardInfo();
        try {
            Tuple7<String, BigInteger, String, String, Boolean, BigInteger, String>  info = contract.getCardInfo(cardId).send();
            cardInfo.setName(info.getValue1());
            cardInfo.setLevel(info.getValue2().intValue());
            cardInfo.setCardId(info.getValue3());
            cardInfo.setUrl(info.getValue4());
            cardInfo.setOnSale(info.getValue5());
            cardInfo.setPrice(String.valueOf(info.getValue6()));
            cardInfo.setOwner(info.getValue7());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardInfo;
    }

    public Integer setCardPrice(String cardId, String price){
        try {
            contract.setCardPrice(cardId, new BigInteger(price)).send();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
