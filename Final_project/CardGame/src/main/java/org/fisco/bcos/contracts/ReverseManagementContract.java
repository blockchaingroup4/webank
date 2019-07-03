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
public class ReverseManagementContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061196c806100206000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635fdb6a651461007d57806378e718f3146100b657806380ae615f146100e157806391494e7014610146578063b1e099081461021b578063e3d4f48f1461029e575b600080fd5b34801561008957600080fd5b506100b4600480360381019080803590602001909291908035151590602001909291905050506102cb565b005b3480156100c257600080fd5b506100cb610f4f565b6040518082815260200191505060405180910390f35b3480156100ed57600080fd5b50610144600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001908201803590602001919091929391929390505050610f5b565b005b34801561015257600080fd5b5061017160048036038101908080359060200190929190505050611466565b6040518087815260200186815260200185151515158152602001806020018415151515815260200183151515158152602001828103825285818151815260200191508051906020019080838360005b838110156101db5780820151818401526020810190506101c0565b50505050905090810190601f1680156102085780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b34801561022757600080fd5b5061029c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611586565b005b3480156102aa57600080fd5b506102c96004803603810190808035906020019092919050505061164e565b005b600080600080600080600080600080600160008d8154811015156102eb57fe5b906000526020600020906005020160040160006101000a81548160ff02191690831515021790555060008c81548110151561032257fe5b906000526020600020906005020160010154995060008c81548110151561034557fe5b906000526020600020906005020160020160009054906101000a900460ff169850600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166327914e9c8a8c6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808315151515815260200182815260200192505050602060405180830381600087803b15801561040357600080fd5b505af1158015610417573d6000803e3d6000fd5b505050506040513d602081101561042d57600080fd5b81019080805190602001909291905050509750600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663dd7bda538b8d6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018215151515815260200192505050600060405180830381600087803b1580156104dd57600080fd5b505af11580156104f1573d6000803e3d6000fd5b505050508a15610f4157600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fc9525198b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561058c57600080fd5b505af11580156105a0573d6000803e3d6000fd5b505050506040513d60208110156105b657600080fd5b81019080805190602001909291905050509650600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b54b4fb98b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561065a57600080fd5b505af115801561066e573d6000803e3d6000fd5b505050506040513d602081101561068457600080fd5b81019080805190602001909291905050509550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635bf608b88b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561072857600080fd5b505af115801561073c573d6000803e3d6000fd5b505050506040513d602081101561075257600080fd5b81019080805190602001909291905050509450600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663d6a9de518b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1580156107f657600080fd5b505af115801561080a573d6000803e3d6000fd5b505050506040513d602081101561082057600080fd5b81019080805190602001909291905050509350600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece866040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156108f057600080fd5b505af1158015610904573d6000803e3d6000fd5b505050506040513d602081101561091a57600080fd5b81019080805190602001909291905050509250600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece856040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156109ea57600080fd5b505af11580156109fe573d6000803e3d6000fd5b505050506040513d6020811015610a1457600080fd5b81019080805190602001909291905050509150600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f6868886016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610aee57600080fd5b505af1158015610b02573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f6858885036040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610bcd57600080fd5b505af1158015610be1573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff168873ffffffffffffffffffffffffffffffffffffffff161415610c2157849050610c25565b8390505b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166377add29d88836040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015610d1657600080fd5b505af1158015610d2a573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166367a26e4685896040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015610e1f57600080fd5b505af1158015610e33573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16638c70260486896040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015610f2857600080fd5b505af1158015610f3c573d6000803e3d6000fd5b505050505b505050505050505050505050565b60008080549050905090565b6000806000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fc952519876040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015610ff157600080fd5b505af1158015611005573d6000803e3d6000fd5b505050506040513d602081101561101b57600080fd5b810190808051906020019092919050505092508673ffffffffffffffffffffffffffffffffffffffff16600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c1551d0d856040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561110257600080fd5b505af1158015611116573d6000803e3d6000fd5b505050506040513d602081101561112c57600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1614151561115f57600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634482950388886040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b15801561122457600080fd5b505af1158015611238573d6000803e3d6000fd5b505050506040513d602081101561124e57600080fd5b81019080805190602001909291905050509150600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663509aa68c876040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b1580156112f257600080fd5b505af1158015611306573d6000803e3d6000fd5b505050506001600060c06040519081016040528060008152602001898152602001851515815260200188888080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505081526020016000151581526020016000151581525090806001815401808255809150509060018203906000526020600020906005020160009091929091909150600082015181600001556020820151816001015560408201518160020160006101000a81548160ff02191690831515021790555060608201518160030190805190602001906113f292919061189b565b5060808201518160040160006101000a81548160ff02191690831515021790555060a08201518160040160016101000a81548160ff02191690831515021790555050500390508060008281548110151561144857fe5b90600052602060002090600502016000018190555050505050505050565b600080600060606000806000808881548110151561148057fe5b90600052602060002090600502019050806000015481600101548260020160009054906101000a900460ff16836003018460040160009054906101000a900460ff168560040160019054906101000a900460ff16828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115695780601f1061153e57610100808354040283529160200191611569565b820191906000526020600020905b81548152906001019060200180831161154c57829003601f168201915b505050505092509650965096509650965096505091939550919395565b82600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b6000806000808481548110151561166157fe5b906000526020600020906005020160010154925060008481548110151561168457fe5b906000526020600020906005020160020160009054906101000a900460ff169150600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166327914e9c83856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808315151515815260200182815260200192505050602060405180830381600087803b15801561174257600080fd5b505af1158015611756573d6000803e3d6000fd5b505050506040513d602081101561176c57600080fd5b81019080805190602001909291905050509050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663035db10e82866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15801561184457600080fd5b505af1158015611858573d6000803e3d6000fd5b50505050600160008581548110151561186d57fe5b906000526020600020906005020160040160016101000a81548160ff02191690831515021790555050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106118dc57805160ff191683800117855561190a565b8280016001018555821561190a579182015b828111156119095782518255916020019190600101906118ee565b5b509050611917919061191b565b5090565b61193d91905b80821115611939576000816000905550600101611921565b5090565b905600a165627a7a72305820bd3bd235427726145649fcb9fcbab8f2a8b6b5ab0644e8426ac13180b4c332240029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"reverseApplyId\",\"type\":\"uint256\"},{\"name\":\"result\",\"type\":\"bool\"}],\"name\":\"setReverseResult\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getReverseAppliesNum\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"transactionId\",\"type\":\"uint256\"},{\"name\":\"discribe\",\"type\":\"string\"}],\"name\":\"createReverseApplies\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"reverseId\",\"type\":\"uint256\"}],\"name\":\"getReverseInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"accountAddr\",\"type\":\"address\"},{\"name\":\"cardAddr\",\"type\":\"address\"},{\"name\":\"transactionAddr\",\"type\":\"address\"}],\"name\":\"setACTInterfaces\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"reverseApplyId\",\"type\":\"uint256\"}],\"name\":\"sendReverseInform\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]";

    public static final String FUNC_SETREVERSERESULT = "setReverseResult";

    public static final String FUNC_GETREVERSEAPPLIESNUM = "getReverseAppliesNum";

    public static final String FUNC_CREATEREVERSEAPPLIES = "createReverseApplies";

    public static final String FUNC_GETREVERSEINFO = "getReverseInfo";

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

    public RemoteCall<Tuple6<BigInteger, BigInteger, Boolean, String, Boolean, Boolean>> getReverseInfo(BigInteger reverseId) {
        final Function function = new Function(FUNC_GETREVERSEINFO, 
                Arrays.<Type>asList(new Uint256(reverseId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple6<BigInteger, BigInteger, Boolean, String, Boolean, Boolean>>(
                new Callable<Tuple6<BigInteger, BigInteger, Boolean, String, Boolean, Boolean>>() {
                    @Override
                    public Tuple6<BigInteger, BigInteger, Boolean, String, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, BigInteger, Boolean, String, Boolean, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
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
