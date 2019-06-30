package org.fisco.bcos.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple5;
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
public class ReverseManagementContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50611a84806100206000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635fdb6a651461008857806378e718f3146100c157806380ae615f146100ec57806391494e7014610151578063ad554c7b1461021b578063b1e09908146102e5578063e3d4f48f14610368575b600080fd5b34801561009457600080fd5b506100bf60048036038101908080359060200190929190803515159060200190929190505050610395565b005b3480156100cd57600080fd5b506100d6610f95565b6040518082815260200191505060405180910390f35b3480156100f857600080fd5b5061014f600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001908201803590602001919091929391929390505050610fa1565b005b34801561015d57600080fd5b5061017c60048036038101908080359060200190929190505050611457565b60405180868152602001858152602001841515151581526020018060200183151515158152602001828103825284818151815260200191508051906020019080838360005b838110156101dc5780820151818401526020810190506101c1565b50505050905090810190601f1680156102095780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b34801561022757600080fd5b5061024660048036038101908080359060200190929190505050611562565b60405180868152602001858152602001841515151581526020018060200183151515158152602001828103825284818151815260200191508051906020019080838360005b838110156102a657808201518184015260208101905061028b565b50505050905090810190601f1680156102d35780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b3480156102f157600080fd5b50610366600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506116d7565b005b34801561037457600080fd5b506103936004803603810190808035906020019092919050505061179f565b005b600080600080600080600080600080600160008d8154811015156103b557fe5b906000526020600020906005020160040160006101000a81548160ff02191690831515021790555060008c8154811015156103ec57fe5b906000526020600020906005020160010154995060008c81548110151561040f57fe5b906000526020600020906005020160020160009054906101000a900460ff169850600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166327914e9c8a8c6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808315151515815260200182815260200192505050602060405180830381600087803b1580156104cd57600080fd5b505af11580156104e1573d6000803e3d6000fd5b505050506040513d60208110156104f757600080fd5b81019080805190602001909291905050509750600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663dd7bda538b8d6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018215151515815260200192505050600060405180830381600087803b1580156105a757600080fd5b505af11580156105bb573d6000803e3d6000fd5b505050508a15610f8757600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fc9525198b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561065657600080fd5b505af115801561066a573d6000803e3d6000fd5b505050506040513d602081101561068057600080fd5b81019080805190602001909291905050509650600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b54b4fb98b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561072457600080fd5b505af1158015610738573d6000803e3d6000fd5b505050506040513d602081101561074e57600080fd5b81019080805190602001909291905050509550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635bf608b88b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1580156107f257600080fd5b505af1158015610806573d6000803e3d6000fd5b505050506040513d602081101561081c57600080fd5b81019080805190602001909291905050509450600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663d6a9de518b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1580156108c057600080fd5b505af11580156108d4573d6000803e3d6000fd5b505050506040513d60208110156108ea57600080fd5b81019080805190602001909291905050509350600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece866040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156109ba57600080fd5b505af11580156109ce573d6000803e3d6000fd5b505050506040513d60208110156109e457600080fd5b81019080805190602001909291905050509250600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece856040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b158015610ab457600080fd5b505af1158015610ac8573d6000803e3d6000fd5b505050506040513d6020811015610ade57600080fd5b81019080805190602001909291905050509150600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f6868886016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610bb857600080fd5b505af1158015610bcc573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f6858885036040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610c9757600080fd5b505af1158015610cab573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff168873ffffffffffffffffffffffffffffffffffffffff161415610ceb57849050610cef565b8390505b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16630b1d9c1e88836040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015610db457600080fd5b505af1158015610dc8573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f9c775ea85896040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610e9157600080fd5b505af1158015610ea5573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632344185c86896040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610f6e57600080fd5b505af1158015610f82573d6000803e3d6000fd5b505050505b505050505050505050505050565b60008080549050905090565b6000806000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fc952519876040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561103757600080fd5b505af115801561104b573d6000803e3d6000fd5b505050506040513d602081101561106157600080fd5b810190808051906020019092919050505092508673ffffffffffffffffffffffffffffffffffffffff16600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663010abbd0856040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561111c57600080fd5b505af1158015611130573d6000803e3d6000fd5b505050506040513d602081101561114657600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1614151561117957600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634482950388886040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b15801561123e57600080fd5b505af1158015611252573d6000803e3d6000fd5b505050506040513d602081101561126857600080fd5b81019080805190602001909291905050509150600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663509aa68c876040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561130c57600080fd5b505af1158015611320573d6000803e3d6000fd5b505050506001600060a06040519081016040528060008152602001898152602001851515815260200188888080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505081526020016000151581525090806001815401808255809150509060018203906000526020600020906005020160009091929091909150600082015181600001556020820151816001015560408201518160020160006101000a81548160ff02191690831515021790555060608201518160030190805190602001906114039291906119b3565b5060808201518160040160006101000a81548160ff02191690831515021790555050500390508060008281548110151561143957fe5b90600052602060002090600502016000018190555050505050505050565b6000806000606060008060008781548110151561147057fe5b90600052602060002090600502019050806000015481600101548260020160009054906101000a900460ff16836003018460040160009054906101000a900460ff16818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115475780601f1061151c57610100808354040283529160200191611547565b820191906000526020600020905b81548152906001019060200180831161152a57829003601f168201915b50505050509150955095509550955095505091939590929450565b600080600060606000808681548110151561157957fe5b90600052602060002090600502016000015460008781548110151561159a57fe5b9060005260206000209060050201600101546000888154811015156115bb57fe5b906000526020600020906005020160020160009054906101000a900460ff166000898154811015156115e957fe5b906000526020600020906005020160030160008a81548110151561160957fe5b906000526020600020906005020160040160009054906101000a900460ff16818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156116bd5780601f10611692576101008083540402835291602001916116bd565b820191906000526020600020905b8154815290600101906020018083116116a057829003601f168201915b505050505091509450945094509450945091939590929450565b82600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b600080600080848154811015156117b257fe5b90600052602060002090600502016001015492506000848154811015156117d557fe5b906000526020600020906005020160020160009054906101000a900460ff169150600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166327914e9c83856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808315151515815260200182815260200192505050602060405180830381600087803b15801561189357600080fd5b505af11580156118a7573d6000803e3d6000fd5b505050506040513d60208110156118bd57600080fd5b81019080805190602001909291905050509050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663035db10e82866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15801561199557600080fd5b505af11580156119a9573d6000803e3d6000fd5b5050505050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106119f457805160ff1916838001178555611a22565b82800160010185558215611a22579182015b82811115611a21578251825591602001919060010190611a06565b5b509050611a2f9190611a33565b5090565b611a5591905b80821115611a51576000816000905550600101611a39565b5090565b905600a165627a7a72305820abccfcc9b2863e97531f753e5c033b01c2ebecd787b0ecb61d57b838a2d682cf0029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"reverseApplyId\",\"type\":\"uint256\"},{\"name\":\"result\",\"type\":\"bool\"}],\"name\":\"setReverseResult\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getReverseAppliesNum\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"transactionId\",\"type\":\"uint256\"},{\"name\":\"discribe\",\"type\":\"string\"}],\"name\":\"createReverseApplies\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"reverseId\",\"type\":\"uint256\"}],\"name\":\"getReverseInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getReverseApply\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"accountAddr\",\"type\":\"address\"},{\"name\":\"cardAddr\",\"type\":\"address\"},{\"name\":\"transactionAddr\",\"type\":\"address\"}],\"name\":\"setACTInterfaces\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"reverseApplyId\",\"type\":\"uint256\"}],\"name\":\"sendReverseInform\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]";

    public static final String FUNC_SETREVERSERESULT = "setReverseResult";

    public static final String FUNC_GETREVERSEAPPLIESNUM = "getReverseAppliesNum";

    public static final String FUNC_CREATEREVERSEAPPLIES = "createReverseApplies";

    public static final String FUNC_GETREVERSEINFO = "getReverseInfo";

    public static final String FUNC_GETREVERSEAPPLY = "getReverseApply";

    public static final String FUNC_SETACTINTERFACES = "setACTInterfaces";

    public static final String FUNC_SENDREVERSEINFORM = "sendReverseInform";

    @Deprecated
    protected ReverseManagementContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ReverseManagementContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ReverseManagementContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ReverseManagementContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setReverseResult(BigInteger reverseApplyId, Boolean result) {
        final Function function = new Function(
                FUNC_SETREVERSERESULT, 
                Arrays.<Type>asList(new Uint256(reverseApplyId),
                new Bool(result)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setReverseResult(BigInteger reverseApplyId, Boolean result, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETREVERSERESULT, 
                Arrays.<Type>asList(new Uint256(reverseApplyId),
                new Bool(result)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setReverseResultSeq(BigInteger reverseApplyId, Boolean result) {
        final Function function = new Function(
                FUNC_SETREVERSERESULT, 
                Arrays.<Type>asList(new Uint256(reverseApplyId),
                new Bool(result)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> getReverseAppliesNum() {
        final Function function = new Function(FUNC_GETREVERSEAPPLIESNUM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> createReverseApplies(String who, BigInteger transactionId, String discribe) {
        final Function function = new Function(
                FUNC_CREATEREVERSEAPPLIES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(who), 
                new Uint256(transactionId),
                new Utf8String(discribe)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createReverseApplies(String who, BigInteger transactionId, String discribe, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATEREVERSEAPPLIES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(who), 
                new Uint256(transactionId),
                new Utf8String(discribe)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createReverseAppliesSeq(String who, BigInteger transactionId, String discribe) {
        final Function function = new Function(
                FUNC_CREATEREVERSEAPPLIES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(who), 
                new Uint256(transactionId),
                new Utf8String(discribe)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple5<BigInteger, BigInteger, Boolean, String, Boolean>> getReverseInfo(BigInteger reverseId) {
        final Function function = new Function(FUNC_GETREVERSEINFO, 
                Arrays.<Type>asList(new Uint256(reverseId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple5<BigInteger, BigInteger, Boolean, String, Boolean>>(
                new Callable<Tuple5<BigInteger, BigInteger, Boolean, String, Boolean>>() {
                    @Override
                    public Tuple5<BigInteger, BigInteger, Boolean, String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, BigInteger, Boolean, String, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<Tuple5<BigInteger, BigInteger, Boolean, String, Boolean>> getReverseApply(BigInteger index) {
        final Function function = new Function(FUNC_GETREVERSEAPPLY, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple5<BigInteger, BigInteger, Boolean, String, Boolean>>(
                new Callable<Tuple5<BigInteger, BigInteger, Boolean, String, Boolean>>() {
                    @Override
                    public Tuple5<BigInteger, BigInteger, Boolean, String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, BigInteger, Boolean, String, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setACTInterfaces(String accountAddr, String cardAddr, String transactionAddr) {
        final Function function = new Function(
                FUNC_SETACTINTERFACES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(accountAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(cardAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(transactionAddr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setACTInterfaces(String accountAddr, String cardAddr, String transactionAddr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETACTINTERFACES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(accountAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(cardAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(transactionAddr)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setACTInterfacesSeq(String accountAddr, String cardAddr, String transactionAddr) {
        final Function function = new Function(
                FUNC_SETACTINTERFACES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(accountAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(cardAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(transactionAddr)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> sendReverseInform(BigInteger reverseApplyId) {
        final Function function = new Function(
                FUNC_SENDREVERSEINFORM, 
                Arrays.<Type>asList(new Uint256(reverseApplyId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void sendReverseInform(BigInteger reverseApplyId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SENDREVERSEINFORM, 
                Arrays.<Type>asList(new Uint256(reverseApplyId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String sendReverseInformSeq(BigInteger reverseApplyId) {
        final Function function = new Function(
                FUNC_SENDREVERSEINFORM, 
                Arrays.<Type>asList(new Uint256(reverseApplyId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    @Deprecated
    public static ReverseManagementContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ReverseManagementContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ReverseManagementContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ReverseManagementContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ReverseManagementContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ReverseManagementContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ReverseManagementContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ReverseManagementContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ReverseManagementContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ReverseManagementContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ReverseManagementContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ReverseManagementContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ReverseManagementContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ReverseManagementContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ReverseManagementContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ReverseManagementContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
