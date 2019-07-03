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
    public static final String BINARY = "608060405234801561001057600080fd5b50610e14806100206000396000f300608060405260043610610099576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306ff46a31461009e578063320721791461015a578063499ec0b6146101b557806370e193521461025557806377add29d146104025780639380b3b3146104655780639d8dd8e0146104b4578063ba96ff1114610501578063c1551d0d14610558575b600080fd5b3480156100aa57600080fd5b506100df600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506105db565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561011f578082015181840152602081019050610104565b50505050905090810190601f16801561014c5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561016657600080fd5b5061019b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506106be565b604051808215151515815260200191505060405180910390f35b3480156101c157600080fd5b50610253600480360381019080803590602001908201803590602001919091929391929390803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001919091929391929390803560000b9060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610716565b005b34801561026157600080fd5b50610296600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610943565b60405180806020018860000b60000b81526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001861515151581526020018581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183810383528a818151815260200191508051906020019080838360005b8381101561035a57808201518184015260208101905061033f565b50505050905090810190601f1680156103875780820380516001836020036101000a031916815260200191505b50838103825287818151815260200191508051906020019080838360005b838110156103c05780820151818401526020810190506103a5565b50505050905090810190601f1680156103ed5780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b34801561040e57600080fd5b50610463600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610b5d565b005b34801561047157600080fd5b506104b2600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803515159060200190929190505050610be1565b005b3480156104c057600080fd5b506104ff600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610c3e565b005b34801561050d57600080fd5b50610542600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c88565b6040518082815260200191505060405180910390f35b34801561056457600080fd5b50610599600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610cd3565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b60606000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106b25780601f10610687576101008083540402835291602001916106b2565b820191906000526020600020905b81548152906001019060200180831161069557829003601f168201915b50505050509050919050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160009054906101000a900460ff169050919050565b60e06040519081016040528088888080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505081526020018360000b81526020018673ffffffffffffffffffffffffffffffffffffffff16815260200185858080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505081526020016000151581526020016201869f81526020018273ffffffffffffffffffffffffffffffffffffffff168152506000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082015181600001908051906020019061083d929190610d43565b5060208201518160010160006101000a81548160ff021916908360000b60ff16021790555060408201518160010160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160020190805190602001906108c5929190610d43565b5060808201518160030160006101000a81548160ff02191690831515021790555060a0820151816004015560c08201518160050160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555090505050505050505050565b606060008060606000806000806000808a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209050806000018160010160009054906101000a900460000b8260010160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16836002018460030160009054906101000a900460ff1685600401548660050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16868054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610aa05780601f10610a7557610100808354040283529160200191610aa0565b820191906000526020600020905b815481529060010190602001808311610a8357829003601f168201915b50505050509650838054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b3c5780601f10610b1157610100808354040283529160200191610b3c565b820191906000526020600020905b815481529060010190602001808311610b1f57829003601f168201915b50505050509350975097509750975097509750975050919395979092949650565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160006101000a81548160ff0219169083151502179055505050565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401819055505050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401549050919050565b6000806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905080915050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610d8457805160ff1916838001178555610db2565b82800160010185558215610db2579182015b82811115610db1578251825591602001919060010190610d96565b5b509050610dbf9190610dc3565b5090565b610de591905b80821115610de1576000816000905550600101610dc9565b5090565b905600a165627a7a723058202cf252aa1673fcf4295ffc293bf1405aecf9ad641a201fc1fa31caab3c572bec0029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"getCardName\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"isCardOnSale\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"url\",\"type\":\"string\"},{\"name\":\"level\",\"type\":\"int8\"},{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"createCard\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"getCardInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"int8\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"setCardOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"onSale\",\"type\":\"bool\"}],\"name\":\"setCardOnSale\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"price\",\"type\":\"uint256\"}],\"name\":\"setCardPrice\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"getPriceOf\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"getCardOwner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}]";

    public static final String FUNC_GETCARDNAME = "getCardName";

    public static final String FUNC_ISCARDONSALE = "isCardOnSale";

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

    public RemoteCall<Boolean> isCardOnSale(String cardId) {
        final Function function = new Function(FUNC_ISCARDONSALE, 
                Arrays.<Type>asList(new Address(cardId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
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
