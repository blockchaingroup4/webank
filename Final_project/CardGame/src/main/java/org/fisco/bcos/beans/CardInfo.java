package org.fisco.bcos.beans;

import lombok.Data;

@Data
public class CardInfo {
    String name;
    Integer level;
    String cardId;
    String url;
    boolean isOnSale;
    String price;
    String owner;
}
