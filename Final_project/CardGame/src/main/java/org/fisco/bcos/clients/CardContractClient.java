package org.fisco.bcos.clients;

import org.fisco.bcos.beans.CardInfo;
import org.fisco.bcos.web3j.crypto.Credentials;

public class CardContractClient extends ContractClient{
    public CardContractClient(Credentials credentials){
        super(credentials);
        load();
    }

    public void load(){

    }

    public CardInfo getCardInfo(String cardId){
        CardInfo info = new CardInfo();
        info.setName("card name");
        info.setCardId("card id");
        info.setLevel(1);
        info.setOnSale(false);
        info.setOwner("card owner");
        info.setPrice("99998877");
        info.setUrl(".....");
        return info;
    }
}
