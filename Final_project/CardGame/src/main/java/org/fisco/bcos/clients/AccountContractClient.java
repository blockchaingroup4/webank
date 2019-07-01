package org.fisco.bcos.clients;

import lombok.Data;
import org.fisco.bcos.beans.AccountInfo;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contracts.AccountManagementContract;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tuples.generated.Tuple6;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class AccountContractClient extends ContractClient{
    AccountManagementContract contract;
    public AccountContractClient(Credentials credentials, String contractAddress, Web3j web3j){
        super(credentials, contractAddress, web3j);
    }

    @Override
    public void load(){
        contract = AccountManagementContract.load(getContractAddress(), web3j, getCredentials(), GasConstants.STATIC_GAS_PROVIDER);
    }

    public Integer addAccount(String name){
        try {
            contract.addAccount(credentials.getAddress(), name).send();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public AccountInfo getAccountInfo(){
        AccountInfo accountInfo = new AccountInfo();
        try {
            Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> info = contract.getAccountInfo(credentials.getAddress()).send();
            accountInfo.setName(info.getValue1());
            accountInfo.setBalance(String.valueOf(info.getValue2()));
            accountInfo.setDrawCount(String.valueOf(info.getValue3()));
            accountInfo.setCardsId(new ArrayList<>());
            accountInfo.setTransactionsId(new ArrayList<>());
            accountInfo.setReversesId(new ArrayList<>());
            int numCard = info.getValue4().intValue();
            int numTrans = info.getValue5().intValue();
            int numRev = info.getValue6().intValue();
            for(int i = 0; i < numCard; i++){
                String id = contract.getCardId(credentials.getAddress(), BigInteger.valueOf(i)).send();
                accountInfo.getCardsId().add(id);
            }
            for(int i = 0; i < numTrans; i++){
                BigInteger id = contract.getTransactionId(credentials.getAddress(), BigInteger.valueOf(i)).send();
                accountInfo.getTransactionsId().add(String.valueOf(id));
            }
            for(int i = 0; i < numRev; i++){
                BigInteger id = contract.getRequestionId(credentials.getAddress(), BigInteger.valueOf(i)).send();
                accountInfo.getReversesId().add(String.valueOf(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return accountInfo;
    }

}
