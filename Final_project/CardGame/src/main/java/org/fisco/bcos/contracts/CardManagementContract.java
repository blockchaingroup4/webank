package org.fisco.bcos.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int8;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class CardManagementContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610aa7806100206000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063010abbd0146100935780630b1d9c1e146101005780634a3f6ecd1461014d5780638c6608d3146101845780638d9f052b146101bd578063970129be14610263578063b54b4fb9146103ce578063ee193c9b1461040f575b600080fd5b34801561009f57600080fd5b506100be60048036038101908080359060200190929190505050610499565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561010c57600080fd5b5061014b60048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506104dd565b005b34801561015957600080fd5b506101826004803603810190808035906020019092919080359060200190929190505050610535565b005b34801561019057600080fd5b506101bb60048036038101908080359060200190929190803515159060200190929190505050610553565b005b3480156101c957600080fd5b506101e860048036038101908080359060200190929190505050610584565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561022857808201518184015260208101905061020d565b50505050905090810190601f1680156102555780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561026f57600080fd5b5061028e6004803603810190808035906020019092919050505061063b565b60405180806020018860000b60000b815260200187815260200180602001861515151581526020018581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183810383528a818151815260200191508051906020019080838360005b8381101561032657808201518184015260208101905061030b565b50505050905090810190601f1680156103535780820380516001836020036101000a031916815260200191505b50838103825287818151815260200191508051906020019080838360005b8381101561038c578082015181840152602081019050610371565b50505050905090810190601f1680156103b95780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b3480156103da57600080fd5b506103f960048036038101908080359060200190929190505050610809565b6040518082815260200191505060405180910390f35b34801561041b57600080fd5b5061049760048036038101908080359060200190820180359060200191909192939192939080359060200190929190803590602001908201803590602001919091929391929390803560000b9060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610828565b005b60008060008084815260200190815260200160002060060160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905080915050919050565b8060008084815260200190815260200160002060060160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b80600080848152602001908152602001600020600501819055505050565b8060008084815260200190815260200160002060040160006101000a81548160ff0219169083151502179055505050565b60606000808381526020019081526020016000206000018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561062f5780601f106106045761010080835404028352916020019161062f565b820191906000526020600020905b81548152906001019060200180831161061257829003601f168201915b50505050509050919050565b606060008060606000806000806000808a81526020019081526020016000209050806000018160010160009054906101000a900460000b8260020154836003018460040160009054906101000a900460ff1685600501548660060160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16868054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561074c5780601f106107215761010080835404028352916020019161074c565b820191906000526020600020905b81548152906001019060200180831161072f57829003601f168201915b50505050509650838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107e85780601f106107bd576101008083540402835291602001916107e8565b820191906000526020600020905b8154815290600101906020018083116107cb57829003601f168201915b50505050509350975097509750975097509750975050919395979092949650565b6000806000838152602001908152602001600020600501549050919050565b60e06040519081016040528088888080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505081526020018360000b815260200186815260200185858080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505081526020016000151581526020016201869f81526020018273ffffffffffffffffffffffffffffffffffffffff16815250600080878152602001908152602001600020600082015181600001908051906020019061090d9291906109d6565b5060208201518160010160006101000a81548160ff021916908360000b60ff1602179055506040820151816002015560608201518160030190805190602001906109589291906109d6565b5060808201518160040160006101000a81548160ff02191690831515021790555060a0820151816005015560c08201518160060160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555090505050505050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a1757805160ff1916838001178555610a45565b82800160010185558215610a45579182015b82811115610a44578251825591602001919060010190610a29565b5b509050610a529190610a56565b5090565b610a7891905b80821115610a74576000816000905550600101610a5c565b5090565b905600a165627a7a72305820f580048a6952a5d0f7dfac48f7ecdca6d1d1400be3595ce029c7c84d074ffe450029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"uint256\"}],\"name\":\"getCardOwner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"uint256\"},{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"setCardOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"uint256\"},{\"name\":\"price\",\"type\":\"uint256\"}],\"name\":\"setCardPrice\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"uint256\"},{\"name\":\"onSale\",\"type\":\"bool\"}],\"name\":\"setCardOnSale\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"uint256\"}],\"name\":\"getCardName\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"uint256\"}],\"name\":\"getCardInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"int8\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"uint256\"}],\"name\":\"getPriceOf\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"cardId\",\"type\":\"uint256\"},{\"name\":\"url\",\"type\":\"string\"},{\"name\":\"level\",\"type\":\"int8\"},{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"createCard\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]";

    public static final String FUNC_GETCARDOWNER = "getCardOwner";

    public static final String FUNC_SETCARDOWNER = "setCardOwner";

    public static final String FUNC_SETCARDPRICE = "setCardPrice";

    public static final String FUNC_SETCARDONSALE = "setCardOnSale";

    public static final String FUNC_GETCARDNAME = "getCardName";

    public static final String FUNC_GETCARDINFO = "getCardInfo";

    public static final String FUNC_GETPRICEOF = "getPriceOf";

    public static final String FUNC_CREATECARD = "createCard";

    @Deprecated
    protected CardManagementContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CardManagementContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CardManagementContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CardManagementContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> getCardOwner(BigInteger cardId) {
        final Function function = new Function(FUNC_GETCARDOWNER, 
                Arrays.<Type>asList(new Uint256(cardId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setCardOwner(BigInteger cardId, String owner) {
        final Function function = new Function(
                FUNC_SETCARDOWNER, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setCardOwner(BigInteger cardId, String owner, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCARDOWNER, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setCardOwnerSeq(BigInteger cardId, String owner) {
        final Function function = new Function(
                FUNC_SETCARDOWNER, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setCardPrice(BigInteger cardId, BigInteger price) {
        final Function function = new Function(
                FUNC_SETCARDPRICE, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setCardPrice(BigInteger cardId, BigInteger price, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCARDPRICE, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setCardPriceSeq(BigInteger cardId, BigInteger price) {
        final Function function = new Function(
                FUNC_SETCARDPRICE, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setCardOnSale(BigInteger cardId, Boolean onSale) {
        final Function function = new Function(
                FUNC_SETCARDONSALE, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Bool(onSale)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setCardOnSale(BigInteger cardId, Boolean onSale, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCARDONSALE, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Bool(onSale)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setCardOnSaleSeq(BigInteger cardId, Boolean onSale) {
        final Function function = new Function(
                FUNC_SETCARDONSALE, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Bool(onSale)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> getCardName(BigInteger cardId) {
        final Function function = new Function(FUNC_GETCARDNAME, 
                Arrays.<Type>asList(new Uint256(cardId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple7<String, BigInteger, BigInteger, String, Boolean, BigInteger, String>> getCardInfo(BigInteger cardId) {
        final Function function = new Function(FUNC_GETCARDINFO, 
                Arrays.<Type>asList(new Uint256(cardId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple7<String, BigInteger, BigInteger, String, Boolean, BigInteger, String>>(
                new Callable<Tuple7<String, BigInteger, BigInteger, String, Boolean, BigInteger, String>>() {
                    @Override
                    public Tuple7<String, BigInteger, BigInteger, String, Boolean, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, BigInteger, BigInteger, String, Boolean, BigInteger, String>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (String) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getPriceOf(BigInteger cardId) {
        final Function function = new Function(FUNC_GETPRICEOF, 
                Arrays.<Type>asList(new Uint256(cardId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> createCard(String name, BigInteger cardId, String url, BigInteger level, String owner) {
        final Function function = new Function(
                FUNC_CREATECARD, 
                Arrays.<Type>asList(new Utf8String(name),
                new Uint256(cardId),
                new Utf8String(url),
                new Int8(level),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createCard(String name, BigInteger cardId, String url, BigInteger level, String owner, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATECARD, 
                Arrays.<Type>asList(new Utf8String(name),
                new Uint256(cardId),
                new Utf8String(url),
                new Int8(level),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createCardSeq(String name, BigInteger cardId, String url, BigInteger level, String owner) {
        final Function function = new Function(
                FUNC_CREATECARD, 
                Arrays.<Type>asList(new Utf8String(name),
                new Uint256(cardId),
                new Utf8String(url),
                new Int8(level),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    @Deprecated
    public static CardManagementContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CardManagementContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CardManagementContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CardManagementContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CardManagementContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CardManagementContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CardManagementContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CardManagementContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CardManagementContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CardManagementContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CardManagementContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CardManagementContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CardManagementContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CardManagementContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CardManagementContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CardManagementContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
