package org.fisco.bcos.beans;

import lombok.Data;

@Data
public class ReverseInfo {
    String reverseId;
    String transactionId;
    boolean role;
    String reason;
    boolean dealed;
    boolean isSent;
}
