package org.fisco.bcos.beans;

import lombok.Data;

import java.util.List;

@Data
public class AccountInfo {
    String name;
    String balance;
    String drawCount;
    List<String>cardsId;
    List<String>transactionsId;
    String address;
}
