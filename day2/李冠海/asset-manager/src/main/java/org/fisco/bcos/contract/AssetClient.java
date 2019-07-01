package org.fisco.bcos.contract;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.List;

public class AssetClient {
    static final String CONTRACT_ADDRESS = "07efc57183fd752df8cbe0acbddeb283cc4e4aa7";
    Web3j web3j;
    Credentials credentials;
    Asset asset;
    public AssetClient(Web3j web3j, Credentials credentials){
        this.web3j = web3j;
        this.credentials = credentials;
    }

    public boolean load(){
        asset = Asset.load(CONTRACT_ADDRESS, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        if(asset == null){
            System.out.println("load contract fail");
            return false;
        }
        return true;
    }

    public static final int TRANSFER_SUCCESS = 0;
    public static final int TRANSFER_SRC_ACCOUNT_NOT_EXISTS = -1;
    public static final int TRANSFER_DST_ACCOUNT_NOT_EXISTS = -2;
    public static final int TRANSFER_AMOUNT_NOT_ENOUTH = -3;
    public static final int TRANSFER_AMOUNT_OVERFLOW = -4;
    public static final int TRANSFER_UNKOWN_ERROR = -5;
    public int transfer(String toAddr, BigInteger amount){
        try {
            TransactionReceipt receipt = asset.transfer(credentials.getAddress(), toAddr, amount).send();
            List<Asset.TransferEventEventResponse> response = asset.getTransferEventEvents(receipt);
            if(response.isEmpty()){
                return TRANSFER_UNKOWN_ERROR;
            }
            System.out.println(response.get(0).ret);
            return response.get(0).ret.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return TRANSFER_UNKOWN_ERROR;
        }
    }

    public static final int REGISTER_SUCCESS = 0;
    public static final int REGISTER_ACCOUNT_ALREAD_EXISTS = -1;
    public static final int REGISTER_UNKOWN_ERROR = -2;
    public int register(BigInteger amount){
        try {
            TransactionReceipt receipt = asset.register(credentials.getAddress(), amount).send();
            List<Asset.RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
            if(response.isEmpty()){
                return REGISTER_UNKOWN_ERROR;
            }
            else{
                return response.get(0).ret.intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return REGISTER_UNKOWN_ERROR;
        }
    }

    public BigInteger getAmount(){
        try {
            Tuple2<BigInteger, BigInteger> res = asset.select(credentials.getAddress()).send();
            if(res.getValue1().equals(BigInteger.ZERO)){
                return res.getValue2();
            }
            else{
                return BigInteger.valueOf(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BigInteger.valueOf(-1);
        }
    }

}
