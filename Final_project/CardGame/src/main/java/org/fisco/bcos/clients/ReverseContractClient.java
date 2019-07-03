package org.fisco.bcos.clients;


import lombok.Data;
import org.fisco.bcos.beans.ReverseInfo;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contracts.ReverseManagementContract;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tuples.generated.Tuple5;
import org.fisco.bcos.web3j.tuples.generated.Tuple6;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ReverseContractClient extends ContractClient{
    ReverseManagementContract contract;
    public ReverseContractClient(Credentials credentials, String contractAddress, Web3j web3j){
        super(credentials, contractAddress, web3j);
    }

    @Override
    public void load(){
        contract = ReverseManagementContract.load(getContractAddress(), getWeb3j(), getCredentials(), GasConstants.STATIC_GAS_PROVIDER);
    }

    public Integer applyForReverse(String transactionId, String reason){
        try {
            contract.createReverseApplies(credentials.getAddress(), new BigInteger(transactionId), reason).send();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public List<ReverseInfo> getReverseApplies(){
        List<ReverseInfo>ret = new ArrayList<>();
        try {
            int numReverse = contract.getReverseAppliesNum().send().intValue();
            for(int i = 0; i < numReverse; i++){
                ret.add(getReverseInfo(String.valueOf(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }
//    public class ReverseInfo {
//        String reverseId;
//        String transactionId;
//        boolean role;
//        String reason;
//        boolean dealed;
//    }
    public ReverseInfo getReverseInfo(String reverseId){
        ReverseInfo ret = new ReverseInfo();
        try {
            Tuple6<BigInteger, BigInteger, Boolean, String, Boolean, Boolean> info = contract.getReverseInfo(new BigInteger(reverseId)).send();
            ret.setReverseId(String.valueOf(info.getValue1()));
            ret.setTransactionId(String.valueOf(info.getValue2()));
            ret.setRole(info.getValue3());
            ret.setReason(info.getValue4());
            ret.setDealed(info.getValue5());
            ret.setSent(info.getValue6());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    public Integer sendReverse(String reverseId){
        try {
            contract.sendReverseInform(new BigInteger(reverseId)).send();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public Integer setReverseResult(String reverseId, Boolean result){
        try {
            contract.setReverseResult(new BigInteger(reverseId), result).send();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}
