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
    public static final String BINARY = "608060405234801561001057600080fd5b50610d56806100206000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306ff46a314610093578063499ec0b61461014f57806370e19352146101ef57806377add29d1461039c5780639380b3b3146103ff5780639d8dd8e01461044e578063ba96ff111461049b578063c1551d0d146104f2575b600080fd5b34801561009f57600080fd5b506100d4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610575565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101145780820151818401526020810190506100f9565b50505050905090810190601f1680156101415780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561015b57600080fd5b506101ed600480360381019080803590602001908201803590602001919091929391929390803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001919091929391929390803560000b9060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610658565b005b3480156101fb57600080fd5b50610230600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610885565b60405180806020018860000b60000b81526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001861515151581526020018581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183810383528a818151815260200191508051906020019080838360005b838110156102f45780820151818401526020810190506102d9565b50505050905090810190601f1680156103215780820380516001836020036101000a031916815260200191505b50838103825287818151815260200191508051906020019080838360005b8381101561035a57808201518184015260208101905061033f565b50505050905090810190601f1680156103875780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b3480156103a857600080fd5b506103fd600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610a9f565b005b34801561040b57600080fd5b5061044c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803515159060200190929190505050610b23565b005b34801561045a57600080fd5b50610499600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610b80565b005b3480156104a757600080fd5b506104dc600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610bca565b6040518082815260200191505060405180910390f35b3480156104fe57600080fd5b50610533600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c15565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b60606000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561064c5780601f106106215761010080835404028352916020019161064c565b820191906000526020600020905b81548152906001019060200180831161062f57829003601f168201915b50505050509050919050565b60e06040519081016040528088888080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505081526020018360000b81526020018673ffffffffffffffffffffffffffffffffffffffff16815260200185858080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505081526020016000151581526020016201869f81526020018273ffffffffffffffffffffffffffffffffffffffff168152506000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082015181600001908051906020019061077f929190610c85565b5060208201518160010160006101000a81548160ff021916908360000b60ff16021790555060408201518160010160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506060820151816002019080519060200190610807929190610c85565b5060808201518160030160006101000a81548160ff02191690831515021790555060a0820151816004015560c08201518160050160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555090505050505050505050565b606060008060606000806000806000808a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209050806000018160010160009054906101000a900460000b8260010160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16836002018460030160009054906101000a900460ff1685600401548660050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16868054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109e25780601f106109b7576101008083540402835291602001916109e2565b820191906000526020600020905b8154815290600101906020018083116109c557829003601f168201915b50505050509650838054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a7e5780601f10610a5357610100808354040283529160200191610a7e565b820191906000526020600020905b815481529060010190602001808311610a6157829003601f168201915b50505050509350975097509750975097509750975050919395979092949650565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160006101000a81548160ff0219169083151502179055505050565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401819055505050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401549050919050565b6000806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905080915050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610cc657805160ff1916838001178555610cf4565b82800160010185558215610cf4579182015b82811115610cf3578251825591602001919060010190610cd8565b5b509050610d019190610d05565b5090565b610d2791905b80821115610d23576000816000905550600101610d0b565b5090565b905600a165627a7a72305820b8d488b8d4ee75085bb0e6a2ab0f8bb533976265f949f5139b8a344e80204fcc0029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"getCardName\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"url\",\"type\":\"string\"},{\"name\":\"level\",\"type\":\"int8\"},{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"createCard\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"getCardInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"int8\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"setCardOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"onSale\",\"type\":\"bool\"}],\"name\":\"setCardOnSale\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"price\",\"type\":\"uint256\"}],\"name\":\"setCardPrice\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"getPriceOf\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"getCardOwner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}]";

    public static final String FUNC_GETCARDNAME = "getCardName";

    public static final String FUNC_CREATECARD = "createCard";

    public static final String FUNC_GETCARDINFO = "getCardInfo";

    public static final String FUNC_SETCARDOWNER = "setCardOwner";

    public static final String FUNC_SETCARDONSALE = "setCardOnSale";

    public static final String FUNC_SETCARDPRICE = "setCardPrice";

    public static final String FUNC_GETPRICEOF = "getPriceOf";

    public static final String FUNC_GETCARDOWNER = "getCardOwner";

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

    public RemoteCall<String> getCardName(String cardId) {
        final Function function = new Function(FUNC_GETCARDNAME, 
                Arrays.<Type>asList(new Address(cardId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> createCard(String name, String cardId, String url, BigInteger level, String owner) {
        final Function function = new Function(
                FUNC_CREATECARD, 
                Arrays.<Type>asList(new Utf8String(name),
                new Address(cardId),
                new Utf8String(url),
                new Int8(level),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createCard(String name, String cardId, String url, BigInteger level, String owner, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATECARD, 
                Arrays.<Type>asList(new Utf8String(name),
                new Address(cardId),
                new Utf8String(url),
                new Int8(level),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createCardSeq(String name, String cardId, String url, BigInteger level, String owner) {
        final Function function = new Function(
                FUNC_CREATECARD, 
                Arrays.<Type>asList(new Utf8String(name),
                new Address(cardId),
                new Utf8String(url),
                new Int8(level),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple7<String, BigInteger, String, String, Boolean, BigInteger, String>> getCardInfo(String cardId) {
        final Function function = new Function(FUNC_GETCARDINFO, 
                Arrays.<Type>asList(new Address(cardId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int8>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple7<String, BigInteger, String, String, Boolean, BigInteger, String>>(
                new Callable<Tuple7<String, BigInteger, String, String, Boolean, BigInteger, String>>() {
                    @Override
                    public Tuple7<String, BigInteger, String, String, Boolean, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, BigInteger, String, String, Boolean, BigInteger, String>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (String) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setCardOwner(String cardId, String owner) {
        final Function function = new Function(
                FUNC_SETCARDOWNER, 
                Arrays.<Type>asList(new Address(cardId),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setCardOwner(String cardId, String owner, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCARDOWNER, 
                Arrays.<Type>asList(new Address(cardId),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setCardOwnerSeq(String cardId, String owner) {
        final Function function = new Function(
                FUNC_SETCARDOWNER, 
                Arrays.<Type>asList(new Address(cardId),
                new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setCardOnSale(String cardId, Boolean onSale) {
        final Function function = new Function(
                FUNC_SETCARDONSALE, 
                Arrays.<Type>asList(new Address(cardId),
                new Bool(onSale)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setCardOnSale(String cardId, Boolean onSale, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCARDONSALE, 
                Arrays.<Type>asList(new Address(cardId),
                new Bool(onSale)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setCardOnSaleSeq(String cardId, Boolean onSale) {
        final Function function = new Function(
                FUNC_SETCARDONSALE, 
                Arrays.<Type>asList(new Address(cardId),
                new Bool(onSale)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setCardPrice(String cardId, BigInteger price) {
        final Function function = new Function(
                FUNC_SETCARDPRICE, 
                Arrays.<Type>asList(new Address(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setCardPrice(String cardId, BigInteger price, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCARDPRICE, 
                Arrays.<Type>asList(new Address(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setCardPriceSeq(String cardId, BigInteger price) {
        final Function function = new Function(
                FUNC_SETCARDPRICE, 
                Arrays.<Type>asList(new Address(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> getPriceOf(String cardId) {
        final Function function = new Function(FUNC_GETPRICEOF, 
                Arrays.<Type>asList(new Address(cardId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getCardOwner(String cardId) {
        final Function function = new Function(FUNC_GETCARDOWNER, 
                Arrays.<Type>asList(new Address(cardId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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
