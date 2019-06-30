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
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple10;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
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
public class TransactionManagementContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610fc9806100206000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806307f2d438146100b457806327914e9c1461017757806333ea3dc8146101f057806344829503146102d7578063509aa68c1461033c5780635bf608b814610369578063b54b4fb9146103d6578063d6a9de5114610417578063d7aa827314610484578063dd7bda53146105f9578063fc95251914610632575b600080fd5b3480156100c057600080fd5b5061016160048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190820180359060200191909192939192939080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061069f565b6040518082815260200191505060405180910390f35b34801561018357600080fd5b506101ae60048036038101908080351515906020019092919080359060200190929190505050610941565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101fc57600080fd5b5061021b600480360381019080803590602001909291905050506109c4565b604051808581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561029957808201518184015260208101905061027e565b50505050905090810190601f1680156102c65780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b3480156102e357600080fd5b50610322600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610aca565b604051808215151515815260200191505060405180910390f35b34801561034857600080fd5b5061036760048036038101908080359060200190929190505050610b59565b005b34801561037557600080fd5b5061039460048036038101908080359060200190929190505050610b95565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156103e257600080fd5b5061040160048036038101908080359060200190929190505050610bde565b6040518082815260200191505060405180910390f35b34801561042357600080fd5b5061044260048036038101908080359060200190929190505050610c0d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561049057600080fd5b506104af60048036038101908080359060200190929190505050610c56565b604051808b81526020018a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018981526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200186815260200185151515158152602001841515151581526020018315151515815260200182810382528a818151815260200191508051906020019080838360005b838110156105b557808201518184015260208101905061059a565b50505050905090810190601f1680156105e25780820380516001836020036101000a031916815260200191505b509b50505050505050505050505060405180910390f35b34801561060557600080fd5b5061063060048036038101908080359060200190929190803515159060200190929190505050610dfc565b005b34801561063e57600080fd5b5061065d60048036038101908080359060200190929190505050610ea9565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b60008060016000610140604051908101604052808c81526020018b73ffffffffffffffffffffffffffffffffffffffff1681526020018a8a8080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505081526020018881526020018773ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1681526020016000815260200160001515815260200160001515815260200160001515815250908060018154018082558091505090600182039060005260206000209060080201600090919290919091506000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002019080519060200190610803929190610ef8565b506060820151816003015560808201518160040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060a08201518160050160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060c0820151816006015560e08201518160070160006101000a81548160ff0219169083151502179055506101008201518160070160016101000a81548160ff0219169083151502179055506101208201518160070160026101000a81548160ff02191690831515021790555050500390508060008281548110151561091d57fe5b90600052602060002090600802016006018190555080915050979650505050505050565b60008060008381548110151561095357fe5b906000526020600020906008020190508315610995578060040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691506109bd565b8060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691505b5092915050565b60008060606000806000868154811015156109db57fe5b9060005260206000209060080201905080600001548160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16826002018360030154818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ab35780601f10610a8857610100808354040283529160200191610ab3565b820191906000526020600020905b815481529060010190602001808311610a9657829003601f168201915b505050505091509450945094509450509193509193565b600080600083815481101515610adc57fe5b906000526020600020906008020190508373ffffffffffffffffffffffffffffffffffffffff168160040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610b4d5760009150610b52565b600191505b5092915050565b6001600082815481101515610b6a57fe5b906000526020600020906008020160070160006101000a81548160ff02191690831515021790555050565b60008082815481101515610ba557fe5b906000526020600020906008020160050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600080600083815481101515610bf057fe5b906000526020600020906008020190508060030154915050919050565b60008082815481101515610c1d57fe5b906000526020600020906008020160040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600080606060008060008060008060008060008c815481101515610c7657fe5b9060005260206000209060080201905080600001548160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168260020183600301548460040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168560050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1686600601548760070160009054906101000a900460ff168860070160019054906101000a900460ff168960070160029054906101000a900460ff16878054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610dd35780601f10610da857610100808354040283529160200191610dd3565b820191906000526020600020905b815481529060010190602001808311610db657829003601f168201915b505050505097509a509a509a509a509a509a509a509a509a509a50509193959799509193959799565b6001600083815481101515610e0d57fe5b906000526020600020906008020160070160016101000a81548160ff02191690831515021790555060008083815481101515610e4557fe5b906000526020600020906008020160070160006101000a81548160ff02191690831515021790555080600083815481101515610e7d57fe5b906000526020600020906008020160070160026101000a81548160ff0219169083151502179055505050565b600080600083815481101515610ebb57fe5b906000526020600020906008020190508060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f3957805160ff1916838001178555610f67565b82800160010185558215610f67579182015b82811115610f66578251825591602001919060010190610f4b565b5b509050610f749190610f78565b5090565b610f9a91905b80821115610f96576000816000905550600101610f7e565b5090565b905600a165627a7a7230582063996fcf94dadccd9295ed4ddd9e02051bb9f4425381e6bb389b449b38314ee70029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"timestamp\",\"type\":\"uint256\"},{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"cardname\",\"type\":\"string\"},{\"name\":\"price\",\"type\":\"uint256\"},{\"name\":\"sellerAddress\",\"type\":\"address\"},{\"name\":\"buyerAddress\",\"type\":\"address\"}],\"name\":\"createTransaction\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"role\",\"type\":\"bool\"},{\"name\":\"transactionId\",\"type\":\"uint256\"}],\"name\":\"getTransaction_re\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"transactionId\",\"type\":\"uint256\"}],\"name\":\"getTransaction\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"transactionId\",\"type\":\"uint256\"}],\"name\":\"getRole\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"transactionId\",\"type\":\"uint256\"}],\"name\":\"setReversingTrue\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"transactionId\",\"type\":\"uint256\"}],\"name\":\"getBuyer\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"transactionId\",\"type\":\"uint256\"}],\"name\":\"getPriceOf\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"transactionId\",\"type\":\"uint256\"}],\"name\":\"getSeller\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"transactionId\",\"type\":\"uint256\"}],\"name\":\"getTransactionInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"transactionId\",\"type\":\"uint256\"},{\"name\":\"result\",\"type\":\"bool\"}],\"name\":\"dealWithRequestions\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"transactionId\",\"type\":\"uint256\"}],\"name\":\"getCardId\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}]";

    public static final String FUNC_CREATETRANSACTION = "createTransaction";

    public static final String FUNC_GETTRANSACTION_RE = "getTransaction_re";

    public static final String FUNC_GETTRANSACTION = "getTransaction";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_SETREVERSINGTRUE = "setReversingTrue";

    public static final String FUNC_GETBUYER = "getBuyer";

    public static final String FUNC_GETPRICEOF = "getPriceOf";

    public static final String FUNC_GETSELLER = "getSeller";

    public static final String FUNC_GETTRANSACTIONINFO = "getTransactionInfo";

    public static final String FUNC_DEALWITHREQUESTIONS = "dealWithRequestions";

    public static final String FUNC_GETCARDID = "getCardId";

    @Deprecated
    protected TransactionManagementContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TransactionManagementContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TransactionManagementContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TransactionManagementContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> createTransaction(BigInteger timestamp, String cardId, String cardname, BigInteger price, String sellerAddress, String buyerAddress) {
        final Function function = new Function(
                FUNC_CREATETRANSACTION, 
                Arrays.<Type>asList(new Uint256(timestamp),
                new Address(cardId),
                new Utf8String(cardname),
                new Uint256(price),
                new Address(sellerAddress),
                new Address(buyerAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createTransaction(BigInteger timestamp, String cardId, String cardname, BigInteger price, String sellerAddress, String buyerAddress, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATETRANSACTION, 
                Arrays.<Type>asList(new Uint256(timestamp),
                new Address(cardId),
                new Utf8String(cardname),
                new Uint256(price),
                new Address(sellerAddress),
                new Address(buyerAddress)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createTransactionSeq(BigInteger timestamp, String cardId, String cardname, BigInteger price, String sellerAddress, String buyerAddress) {
        final Function function = new Function(
                FUNC_CREATETRANSACTION, 
                Arrays.<Type>asList(new Uint256(timestamp),
                new Address(cardId),
                new Utf8String(cardname),
                new Uint256(price),
                new Address(sellerAddress),
                new Address(buyerAddress)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> getTransaction_re(Boolean role, BigInteger transactionId) {
        final Function function = new Function(FUNC_GETTRANSACTION_RE, 
                Arrays.<Type>asList(new Bool(role),
                new Uint256(transactionId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple4<BigInteger, String, String, BigInteger>> getTransaction(BigInteger transactionId) {
        final Function function = new Function(FUNC_GETTRANSACTION, 
                Arrays.<Type>asList(new Uint256(transactionId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple4<BigInteger, String, String, BigInteger>>(
                new Callable<Tuple4<BigInteger, String, String, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, String, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<Boolean> getRole(String who, BigInteger transactionId) {
        final Function function = new Function(FUNC_GETROLE, 
                Arrays.<Type>asList(new Address(who),
                new Uint256(transactionId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> setReversingTrue(BigInteger transactionId) {
        final Function function = new Function(
                FUNC_SETREVERSINGTRUE, 
                Arrays.<Type>asList(new Uint256(transactionId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setReversingTrue(BigInteger transactionId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETREVERSINGTRUE, 
                Arrays.<Type>asList(new Uint256(transactionId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setReversingTrueSeq(BigInteger transactionId) {
        final Function function = new Function(
                FUNC_SETREVERSINGTRUE, 
                Arrays.<Type>asList(new Uint256(transactionId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> getBuyer(BigInteger transactionId) {
        final Function function = new Function(FUNC_GETBUYER, 
                Arrays.<Type>asList(new Uint256(transactionId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getPriceOf(BigInteger transactionId) {
        final Function function = new Function(FUNC_GETPRICEOF, 
                Arrays.<Type>asList(new Uint256(transactionId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getSeller(BigInteger transactionId) {
        final Function function = new Function(FUNC_GETSELLER, 
                Arrays.<Type>asList(new Uint256(transactionId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple10<BigInteger, String, String, BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean>> getTransactionInfo(BigInteger transactionId) {
        final Function function = new Function(FUNC_GETTRANSACTIONINFO, 
                Arrays.<Type>asList(new Uint256(transactionId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple10<BigInteger, String, String, BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean>>(
                new Callable<Tuple10<BigInteger, String, String, BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean>>() {
                    @Override
                    public Tuple10<BigInteger, String, String, BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple10<BigInteger, String, String, BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (Boolean) results.get(7).getValue(), 
                                (Boolean) results.get(8).getValue(), 
                                (Boolean) results.get(9).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> dealWithRequestions(BigInteger transactionId, Boolean result) {
        final Function function = new Function(
                FUNC_DEALWITHREQUESTIONS, 
                Arrays.<Type>asList(new Uint256(transactionId),
                new Bool(result)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void dealWithRequestions(BigInteger transactionId, Boolean result, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_DEALWITHREQUESTIONS, 
                Arrays.<Type>asList(new Uint256(transactionId),
                new Bool(result)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String dealWithRequestionsSeq(BigInteger transactionId, Boolean result) {
        final Function function = new Function(
                FUNC_DEALWITHREQUESTIONS, 
                Arrays.<Type>asList(new Uint256(transactionId),
                new Bool(result)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> getCardId(BigInteger transactionId) {
        final Function function = new Function(FUNC_GETCARDID, 
                Arrays.<Type>asList(new Uint256(transactionId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static TransactionManagementContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TransactionManagementContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TransactionManagementContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TransactionManagementContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TransactionManagementContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TransactionManagementContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TransactionManagementContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TransactionManagementContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TransactionManagementContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TransactionManagementContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TransactionManagementContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TransactionManagementContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TransactionManagementContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TransactionManagementContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TransactionManagementContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TransactionManagementContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
