package org.fisco.bcos.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint32;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple6;
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
public class AccountManagementContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50611355806100206000396000f3006080604052600436106100e6576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063035db10e146100eb578063141153ba14610138578063159db4851461018f5780635ed75180146101f25780636086ce1b1461025357806367a26e46146102a65780637b510fe81461030957806380578309146103f457806384a372731461044b5780638c702604146104985780639b96eece146104fb578063a20d2f7314610552578063b46310f6146105df578063b57b1db91461062c578063e783a1e614610683578063f0d4bc5c146106e4575b600080fd5b3480156100f757600080fd5b50610136600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061076d565b005b34801561014457600080fd5b50610179600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506107dc565b6040518082815260200191505060405180910390f35b34801561019b57600080fd5b506101d0600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061082a565b604051808263ffffffff1663ffffffff16815260200191505060405180910390f35b3480156101fe57600080fd5b5061023d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610885565b6040518082815260200191505060405180910390f35b34801561025f57600080fd5b506102a4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803563ffffffff1690602001909291905050506108e8565b005b3480156102b257600080fd5b50610307600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061094c565b005b34801561031557600080fd5b5061034a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506109f5565b60405180806020018781526020018663ffffffff1663ffffffff168152602001858152602001848152602001838152602001828103825288818151815260200191508051906020019080838360005b838110156103b4578082015181840152602081019050610399565b50505050905090810190601f1680156103e15780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b34801561040057600080fd5b50610435600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610b62565b6040518082815260200191505060405180910390f35b34801561045757600080fd5b50610496600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610bb0565b005b3480156104a457600080fd5b506104f9600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c1f565b005b34801561050757600080fd5b5061053c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610dc5565b6040518082815260200191505060405180910390f35b34801561055e57600080fd5b5061059d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610e10565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156105eb57600080fd5b5061062a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610e93565b005b34801561063857600080fd5b5061066d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610edd565b6040518082815260200191505060405180910390f35b34801561068f57600080fd5b506106ce600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610f2b565b6040518082815260200191505060405180910390f35b3480156106f057600080fd5b5061076b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610f8e565b005b6000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206005018190806001815401808255809150509060018203906000526020600020016000909192909190915055505050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401805490509050919050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160009054906101000a900463ffffffff169050919050565b60008060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600501828154811015156108d557fe5b9060005260206000200154905092915050565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160006101000a81548163ffffffff021916908363ffffffff1602179055505050565b6000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206003018190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b60606000806000806000808773ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610a3a57600080fd5b6000808973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060000181600101548260020160009054906101000a900463ffffffff16836003018054905084600401805490508560050180549050858054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b455780601f10610b1a57610100808354040283529160200191610b45565b820191906000526020600020905b815481529060010190602001808311610b2857829003601f168201915b505050505095509650965096509650965096505091939550919395565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600501805490509050919050565b6000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004018190806001815401808255809150509060018203906000526020600020016000909192909190915055505050565b60008060008060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206003019250600091505b8280549050821015610dbd578373ffffffffffffffffffffffffffffffffffffffff168383815481101515610c9d57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610db0578190505b6001838054905003811015610d96578260018201815481101515610d0857fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168382815481101515610d4157fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508080600101915050610ce8565b82805480919060019003610daa919061113e565b50610dbe565b8180600101925050610c6c565b5b5050505050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101549050919050565b60008060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030182815481101515610e6057fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905092915050565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101819055505050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600301805490509050919050565b60008060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040182815481101515610f7b57fe5b9060005260206000200154905092915050565b60c06040519081016040528082815260200160008152602001600563ffffffff1681526020016000604051908082528060200260200182016040528015610fe45781602001602082028038833980820191505090505b508152602001600060405190808252806020026020018201604052801561101a5781602001602082028038833980820191505090505b50815260200160006040519080825280602002602001820160405280156110505781602001602082028038833980820191505090505b508152506000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000190805190602001906110ae92919061116a565b506020820151816001015560408201518160020160006101000a81548163ffffffff021916908363ffffffff16021790555060608201518160030190805190602001906110fc9291906111ea565b506080820151816004019080519060200190611119929190611274565b5060a0820151816005019080519060200190611136929190611274565b509050505050565b8154818355818111156111655781836000526020600020918201910161116491906112c1565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106111ab57805160ff19168380011785556111d9565b828001600101855582156111d9579182015b828111156111d85782518255916020019190600101906111bd565b5b5090506111e691906112c1565b5090565b828054828255906000526020600020908101928215611263579160200282015b828111156112625782518260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055509160200191906001019061120a565b5b50905061127091906112e6565b5090565b8280548282559060005260206000209081019282156112b0579160200282015b828111156112af578251825591602001919060010190611294565b5b5090506112bd91906112c1565b5090565b6112e391905b808211156112df5760008160009055506001016112c7565b5090565b90565b61132691905b8082111561132257600081816101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055506001016112ec565b5090565b905600a165627a7a723058204f623ff7d330a1af5fe7669bdee8dc26f565bbecf102a9184a4071cfce1a62d90029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"requestionId\",\"type\":\"uint256\"}],\"name\":\"addRequestions\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"}],\"name\":\"getTransactionsNum\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getDrawCountOf\",\"outputs\":[{\"name\":\"\",\"type\":\"uint32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getRequestionId\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"},{\"name\":\"count\",\"type\":\"uint32\"}],\"name\":\"setDrawCountOf\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"addCard\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"}],\"name\":\"getAccountInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint32\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"}],\"name\":\"getRequestionsNum\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"transactionId\",\"type\":\"uint256\"}],\"name\":\"addTransaction\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"owner\",\"type\":\"address\"},{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"removeCard\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getBalanceOf\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getCardId\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"},{\"name\":\"balance\",\"type\":\"uint256\"}],\"name\":\"setBalanceOf\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"}],\"name\":\"getCardsNum\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getTransactionId\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"},{\"name\":\"name\",\"type\":\"string\"}],\"name\":\"addAccount\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]";

    public static final String FUNC_ADDREQUESTIONS = "addRequestions";

    public static final String FUNC_GETTRANSACTIONSNUM = "getTransactionsNum";

    public static final String FUNC_GETDRAWCOUNTOF = "getDrawCountOf";

    public static final String FUNC_GETREQUESTIONID = "getRequestionId";

    public static final String FUNC_SETDRAWCOUNTOF = "setDrawCountOf";

    public static final String FUNC_ADDCARD = "addCard";

    public static final String FUNC_GETACCOUNTINFO = "getAccountInfo";

    public static final String FUNC_GETREQUESTIONSNUM = "getRequestionsNum";

    public static final String FUNC_ADDTRANSACTION = "addTransaction";

    public static final String FUNC_REMOVECARD = "removeCard";

    public static final String FUNC_GETBALANCEOF = "getBalanceOf";

    public static final String FUNC_GETCARDID = "getCardId";

    public static final String FUNC_SETBALANCEOF = "setBalanceOf";

    public static final String FUNC_GETCARDSNUM = "getCardsNum";

    public static final String FUNC_GETTRANSACTIONID = "getTransactionId";

    public static final String FUNC_ADDACCOUNT = "addAccount";

    @Deprecated
    protected AccountManagementContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AccountManagementContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AccountManagementContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AccountManagementContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> addRequestions(String who, BigInteger requestionId) {
        final Function function = new Function(
                FUNC_ADDREQUESTIONS, 
                Arrays.<Type>asList(new Address(who),
                new Uint256(requestionId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addRequestions(String who, BigInteger requestionId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDREQUESTIONS, 
                Arrays.<Type>asList(new Address(who),
                new Uint256(requestionId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addRequestionsSeq(String who, BigInteger requestionId) {
        final Function function = new Function(
                FUNC_ADDREQUESTIONS, 
                Arrays.<Type>asList(new Address(who),
                new Uint256(requestionId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> getTransactionsNum(String who) {
        final Function function = new Function(FUNC_GETTRANSACTIONSNUM, 
                Arrays.<Type>asList(new Address(who)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getDrawCountOf(String addr) {
        final Function function = new Function(FUNC_GETDRAWCOUNTOF, 
                Arrays.<Type>asList(new Address(addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getRequestionId(String who, BigInteger index) {
        final Function function = new Function(FUNC_GETREQUESTIONID, 
                Arrays.<Type>asList(new Address(who),
                new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setDrawCountOf(String addr, BigInteger count) {
        final Function function = new Function(
                FUNC_SETDRAWCOUNTOF, 
                Arrays.<Type>asList(new Address(addr),
                new Uint32(count)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setDrawCountOf(String addr, BigInteger count, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETDRAWCOUNTOF, 
                Arrays.<Type>asList(new Address(addr),
                new Uint32(count)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setDrawCountOfSeq(String addr, BigInteger count) {
        final Function function = new Function(
                FUNC_SETDRAWCOUNTOF, 
                Arrays.<Type>asList(new Address(addr),
                new Uint32(count)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> addCard(String who, String cardId) {
        final Function function = new Function(
                FUNC_ADDCARD, 
                Arrays.<Type>asList(new Address(who),
                new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addCard(String who, String cardId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDCARD, 
                Arrays.<Type>asList(new Address(who),
                new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addCardSeq(String who, String cardId) {
        final Function function = new Function(
                FUNC_ADDCARD, 
                Arrays.<Type>asList(new Address(who),
                new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> getAccountInfo(String who) {
        final Function function = new Function(FUNC_GETACCOUNTINFO, 
                Arrays.<Type>asList(new Address(who)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getRequestionsNum(String who) {
        final Function function = new Function(FUNC_GETREQUESTIONSNUM, 
                Arrays.<Type>asList(new Address(who)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addTransaction(String who, BigInteger transactionId) {
        final Function function = new Function(
                FUNC_ADDTRANSACTION, 
                Arrays.<Type>asList(new Address(who),
                new Uint256(transactionId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addTransaction(String who, BigInteger transactionId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDTRANSACTION, 
                Arrays.<Type>asList(new Address(who),
                new Uint256(transactionId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addTransactionSeq(String who, BigInteger transactionId) {
        final Function function = new Function(
                FUNC_ADDTRANSACTION, 
                Arrays.<Type>asList(new Address(who),
                new Uint256(transactionId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> removeCard(String owner, String cardId) {
        final Function function = new Function(
                FUNC_REMOVECARD, 
                Arrays.<Type>asList(new Address(owner),
                new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void removeCard(String owner, String cardId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REMOVECARD, 
                Arrays.<Type>asList(new Address(owner),
                new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String removeCardSeq(String owner, String cardId) {
        final Function function = new Function(
                FUNC_REMOVECARD, 
                Arrays.<Type>asList(new Address(owner),
                new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> getBalanceOf(String addr) {
        final Function function = new Function(FUNC_GETBALANCEOF, 
                Arrays.<Type>asList(new Address(addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getCardId(String who, BigInteger index) {
        final Function function = new Function(FUNC_GETCARDID, 
                Arrays.<Type>asList(new Address(who),
                new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setBalanceOf(String addr, BigInteger balance) {
        final Function function = new Function(
                FUNC_SETBALANCEOF, 
                Arrays.<Type>asList(new Address(addr),
                new Uint256(balance)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBalanceOf(String addr, BigInteger balance, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBALANCEOF, 
                Arrays.<Type>asList(new Address(addr),
                new Uint256(balance)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setBalanceOfSeq(String addr, BigInteger balance) {
        final Function function = new Function(
                FUNC_SETBALANCEOF, 
                Arrays.<Type>asList(new Address(addr),
                new Uint256(balance)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> getCardsNum(String who) {
        final Function function = new Function(FUNC_GETCARDSNUM, 
                Arrays.<Type>asList(new Address(who)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getTransactionId(String who, BigInteger index) {
        final Function function = new Function(FUNC_GETTRANSACTIONID, 
                Arrays.<Type>asList(new Address(who),
                new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addAccount(String addr, String name) {
        final Function function = new Function(
                FUNC_ADDACCOUNT, 
                Arrays.<Type>asList(new Address(addr),
                new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addAccount(String addr, String name, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDACCOUNT, 
                Arrays.<Type>asList(new Address(addr),
                new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addAccountSeq(String addr, String name) {
        final Function function = new Function(
                FUNC_ADDACCOUNT, 
                Arrays.<Type>asList(new Address(addr),
                new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    @Deprecated
    public static AccountManagementContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AccountManagementContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AccountManagementContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AccountManagementContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AccountManagementContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AccountManagementContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AccountManagementContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AccountManagementContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AccountManagementContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AccountManagementContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AccountManagementContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AccountManagementContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<AccountManagementContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AccountManagementContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AccountManagementContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AccountManagementContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
