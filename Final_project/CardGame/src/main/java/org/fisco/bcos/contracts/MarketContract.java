package org.fisco.bcos.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int8;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.BcosFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class MarketContract extends Contract {
    public static final String BINARY = "6080604052600060045534801561001557600080fd5b50612416806100256000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063139f9aa9146100b45780632a985d49146100f757806336351c7c146101385780633b1e6bf0146101855780634ecb54b0146101c85780635ab8782b146102235780637dd9f84614610266578063983f6766146102b35780639e095451146102fe578063ad2b232f14610388578063b1e09908146103e9575b600080fd5b3480156100c057600080fd5b506100f5600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061046c565b005b34801561010357600080fd5b50610122600480360381019080803590602001909291905050506104b0565b6040518082815260200191505060405180910390f35b34801561014457600080fd5b50610183600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610578565b005b34801561019157600080fd5b506101c6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610758565b005b3480156101d457600080fd5b50610221600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190820180359060200191909192939192939050505061079c565b005b34801561022f57600080fd5b50610264600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610aea565b005b34801561027257600080fd5b506102b1600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610b2e565b005b3480156102bf57600080fd5b506102e8600480360381019080803590602001909291908035906020019092919050505061187d565b6040518082815260200191505060405180910390f35b34801561030a57600080fd5b50610386600480360381019080803590602001908201803590602001919091929391929390803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001908201803590602001919091929391929390803560000b9060200190929190505050611a1b565b005b34801561039457600080fd5b506103d3600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611e12565b6040518082815260200191505060405180910390f35b3480156103f557600080fd5b5061046a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050612207565b005b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16638c6608d38360006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018215151515815260200192505050600060405180830381600087803b15801561055057600080fd5b505af1158015610564573d6000803e3d6000fd5b50505050610571826122cf565b9050919050565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece846040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561063757600080fd5b505af115801561064b573d6000803e3d6000fd5b505050506040513d602081101561066157600080fd5b81019080805190602001909291905050509050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f6848484016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15801561073b57600080fd5b505af115801561074f573d6000803e3d6000fd5b50505050505050565b80600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b600080600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663159db485886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561085f57600080fd5b505af1158015610873573d6000803e3d6000fd5b505050506040513d602081101561088957600080fd5b810190808051906020019092919050505063ffffffff161115156108ac57600080fd5b4233600454604051602001808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166c0100000000000000000000000002815260140182815260200193505050506040516020818303038152906040526040518082805190602001908083835b60208310151561094e5780518252602082019150602081019050602083039250610929565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600190048585604051602001808383808284378201915050925050506040516020818303038152906040526040518082805190602001908083835b6020831015156109db57805182526020820191506020810190506020830392506109b6565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206001900401925061271083811515610a1c57fe5b06915060046000815480929190600101919050555060009050611388821015610a485760019050610a9d565b611d4c821015610a5b5760029050610a9c565b612166821015610a6e5760039050610a9b565b6124ae821015610a815760049050610a9a565b6126a2821015610a945760059050610a99565b600690505b5b5b5b5b7f114a22007f2f524d773975642d9a21c377907b826d19da7fe68d7a1322778a998184604051808360000b60000b81526020018281526020019250505060405180910390a1505050505050565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000806000806060600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece8a6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b158015610bf657600080fd5b505af1158015610c0a573d6000803e3d6000fd5b505050506040513d6020811015610c2057600080fd5b81019080805190602001909291905050509650600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663010abbd0896040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015610cc457600080fd5b505af1158015610cd8573d6000803e3d6000fd5b505050506040513d6020811015610cee57600080fd5b81019080805190602001909291905050509550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece876040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b158015610dbe57600080fd5b505af1158015610dd2573d6000803e3d6000fd5b505050506040513d6020811015610de857600080fd5b81019080805190602001909291905050509450600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b54b4fb9896040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015610e8c57600080fd5b505af1158015610ea0573d6000803e3d6000fd5b505050506040513d6020811015610eb657600080fd5b81019080805190602001909291905050509350600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16638d9f052b896040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610f5a57600080fd5b505af1158015610f6e573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506020811015610f9857600080fd5b810190808051640100000000811115610fb057600080fd5b82810190506020810184811115610fc657600080fd5b8151856001820283011164010000000082111715610fe357600080fd5b50509291905050509250600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16630b1d9c1e898b6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b1580156110b257600080fd5b505af11580156110c6573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16638c6608d38960006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018215151515815260200192505050600060405180830381600087803b15801561116857600080fd5b505af115801561117c573d6000803e3d6000fd5b50505050889150600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166310b850b1428a86888b886040518763ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180878152602001868152602001806020018581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825286818151815260200191508051906020019080838360005b838110156112a857808201518184015260208101905061128d565b50505050905090810190601f1680156112d55780820380516001836020036101000a031916815260200191505b50975050505050505050602060405180830381600087803b1580156112f957600080fd5b505af115801561130d573d6000803e3d6000fd5b505050506040513d602081101561132357600080fd5b81019080805190602001909291905050509050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632344185c878a6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1580156113fb57600080fd5b505af115801561140f573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f9c775ea8a8a6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1580156114d857600080fd5b505af11580156114ec573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166384a372738a836040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1580156115b557600080fd5b505af11580156115c9573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166384a3727387836040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15801561169257600080fd5b505af11580156116a6573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f68a868a036040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15801561177157600080fd5b505af1158015611785573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f6878688016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15801561185057600080fd5b505af1158015611864573d6000803e3d6000fd5b50505050611871886122cf565b50505050505050505050565b600080839080600181540180825580915050906001820390600052602060002001600090919290919091505550600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16638c6608d38460016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018215151515815260200192505050600060405180830381600087803b15801561194857600080fd5b505af115801561195c573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634a3f6ecd84846040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083815260200182815260200192505050600060405180830381600087803b1580156119f957600080fd5b505af1158015611a0d573d6000803e3d6000fd5b505050506000905092915050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ee193c9b8888878787878c6040518863ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001878152602001806020018560000b60000b81526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183810383528a8a828181526020019250808284378201915050838103825287878281815260200192508082843782019150509950505050505050505050600060405180830381600087803b158015611b3257600080fd5b505af1158015611b46573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f9c775ea86866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015611c0f57600080fd5b505af1158015611c23573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16636086ce1b8660018060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663159db4858a6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b158015611d2457600080fd5b505af1158015611d38573d6000803e3d6000fd5b505050506040513d6020811015611d4e57600080fd5b8101908080519060200190929190505050036040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018263ffffffff1663ffffffff16815260200192505050600060405180830381600087803b158015611df157600080fd5b505af1158015611e05573d6000803e3d6000fd5b5050505050505050505050565b6000806000806000600a9350600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b158015611edb57600080fd5b505af1158015611eef573d6000803e3d6000fd5b505050506040513d6020811015611f0557600080fd5b81019080805190602001909291905050509250838602915082821115611f2e57600194506121fd565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f6888486036040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015611ff557600080fd5b505af1158015612009573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663159db485886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156120ca57600080fd5b505af11580156120de573d6000803e3d6000fd5b505050506040513d60208110156120f457600080fd5b81019080805190602001909291905050509050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16636086ce1b88888463ffffffff16016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018263ffffffff1663ffffffff16815260200192505050600060405180830381600087803b1580156121e057600080fd5b505af11580156121f4573d6000803e3d6000fd5b50505050600094505b5050505092915050565b82600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b60008060008091505b60008054905082101561238d57836000838154811015156122f557fe5b90600052602060002001541415612380578190505b6001600080549050038110156123615760006001820181548110151561232c57fe5b906000526020600020015460008281548110151561234657fe5b9060005260206000200181905550808060010191505061230a565b60008054809190600190036123769190612399565b5060009250612392565b81806001019250506122d8565b600192505b5050919050565b8154818355818111156123c0578183600052602060002091820191016123bf91906123c5565b5b505050565b6123e791905b808211156123e35760008160009055506001016123cb565b5090565b905600a165627a7a72305820950d5336a4bc5ad90599451dab4594eee2f57cade5032f1bf4b71c82bb8013dc0029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"setCardManagementInterface\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"uint256\"}],\"name\":\"pullCard\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"recharge\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"setTransactionManagementInterface\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"wish\",\"type\":\"string\"}],\"name\":\"drawCard\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"setAccoundManagementInterface\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"buyer\",\"type\":\"address\"},{\"name\":\"cardId\",\"type\":\"uint256\"}],\"name\":\"buyCard\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"uint256\"},{\"name\":\"price\",\"type\":\"uint256\"}],\"name\":\"pushCard\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"cardId\",\"type\":\"uint256\"},{\"name\":\"url\",\"type\":\"string\"},{\"name\":\"level\",\"type\":\"int8\"}],\"name\":\"createCardAndGiveTo\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"},{\"name\":\"times\",\"type\":\"uint256\"}],\"name\":\"buyDrawCards\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"accountAddr\",\"type\":\"address\"},{\"name\":\"cardAddr\",\"type\":\"address\"},{\"name\":\"transactionAddr\",\"type\":\"address\"}],\"name\":\"setACTInterfaces\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"level\",\"type\":\"int8\"},{\"indexed\":false,\"name\":\"cardId\",\"type\":\"uint256\"}],\"name\":\"DrawCardEvent\",\"type\":\"event\"}]";

    public static final String FUNC_SETCARDMANAGEMENTINTERFACE = "setCardManagementInterface";

    public static final String FUNC_PULLCARD = "pullCard";

    public static final String FUNC_RECHARGE = "recharge";

    public static final String FUNC_SETTRANSACTIONMANAGEMENTINTERFACE = "setTransactionManagementInterface";

    public static final String FUNC_DRAWCARD = "drawCard";

    public static final String FUNC_SETACCOUNDMANAGEMENTINTERFACE = "setAccoundManagementInterface";

    public static final String FUNC_BUYCARD = "buyCard";

    public static final String FUNC_PUSHCARD = "pushCard";

    public static final String FUNC_CREATECARDANDGIVETO = "createCardAndGiveTo";

    public static final String FUNC_BUYDRAWCARDS = "buyDrawCards";

    public static final String FUNC_SETACTINTERFACES = "setACTInterfaces";

    public static final Event DRAWCARDEVENT_EVENT = new Event("DrawCardEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int8>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected MarketContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MarketContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MarketContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MarketContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setCardManagementInterface(String addr) {
        final Function function = new Function(
                FUNC_SETCARDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setCardManagementInterface(String addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCARDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setCardManagementInterfaceSeq(String addr) {
        final Function function = new Function(
                FUNC_SETCARDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> pullCard(BigInteger cardId) {
        final Function function = new Function(
                FUNC_PULLCARD, 
                Arrays.<Type>asList(new Uint256(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void pullCard(BigInteger cardId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PULLCARD, 
                Arrays.<Type>asList(new Uint256(cardId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String pullCardSeq(BigInteger cardId) {
        final Function function = new Function(
                FUNC_PULLCARD, 
                Arrays.<Type>asList(new Uint256(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> recharge(String addr, BigInteger amount) {
        final Function function = new Function(
                FUNC_RECHARGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr), 
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void recharge(String addr, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_RECHARGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr), 
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String rechargeSeq(String addr, BigInteger amount) {
        final Function function = new Function(
                FUNC_RECHARGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr), 
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setTransactionManagementInterface(String addr) {
        final Function function = new Function(
                FUNC_SETTRANSACTIONMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setTransactionManagementInterface(String addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETTRANSACTIONMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setTransactionManagementInterfaceSeq(String addr) {
        final Function function = new Function(
                FUNC_SETTRANSACTIONMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> drawCard(String who, String wish) {
        final Function function = new Function(
                FUNC_DRAWCARD, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(who), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(wish)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void drawCard(String who, String wish, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_DRAWCARD, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(who), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(wish)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String drawCardSeq(String who, String wish) {
        final Function function = new Function(
                FUNC_DRAWCARD, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(who), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(wish)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setAccoundManagementInterface(String addr) {
        final Function function = new Function(
                FUNC_SETACCOUNDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setAccoundManagementInterface(String addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETACCOUNDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setAccoundManagementInterfaceSeq(String addr) {
        final Function function = new Function(
                FUNC_SETACCOUNDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> buyCard(String buyer, BigInteger cardId) {
        final Function function = new Function(
                FUNC_BUYCARD, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(buyer), 
                new Uint256(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void buyCard(String buyer, BigInteger cardId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_BUYCARD, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(buyer), 
                new Uint256(cardId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String buyCardSeq(String buyer, BigInteger cardId) {
        final Function function = new Function(
                FUNC_BUYCARD, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(buyer), 
                new Uint256(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> pushCard(BigInteger cardId, BigInteger price) {
        final Function function = new Function(
                FUNC_PUSHCARD, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void pushCard(BigInteger cardId, BigInteger price, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PUSHCARD, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String pushCardSeq(BigInteger cardId, BigInteger price) {
        final Function function = new Function(
                FUNC_PUSHCARD, 
                Arrays.<Type>asList(new Uint256(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> createCardAndGiveTo(String name, String who, BigInteger cardId, String url, BigInteger level) {
        final Function function = new Function(
                FUNC_CREATECARDANDGIVETO, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(who), 
                new Uint256(cardId),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(url), 
                new Int8(level)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createCardAndGiveTo(String name, String who, BigInteger cardId, String url, BigInteger level, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATECARDANDGIVETO, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(who), 
                new Uint256(cardId),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(url), 
                new Int8(level)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createCardAndGiveToSeq(String name, String who, BigInteger cardId, String url, BigInteger level) {
        final Function function = new Function(
                FUNC_CREATECARDANDGIVETO, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(who), 
                new Uint256(cardId),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(url), 
                new Int8(level)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> buyDrawCards(String addr, BigInteger times) {
        final Function function = new Function(
                FUNC_BUYDRAWCARDS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr), 
                new Uint256(times)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void buyDrawCards(String addr, BigInteger times, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_BUYDRAWCARDS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr), 
                new Uint256(times)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String buyDrawCardsSeq(String addr, BigInteger times) {
        final Function function = new Function(
                FUNC_BUYDRAWCARDS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr), 
                new Uint256(times)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
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

    public List<DrawCardEventEventResponse> getDrawCardEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(DRAWCARDEVENT_EVENT, transactionReceipt);
        ArrayList<DrawCardEventEventResponse> responses = new ArrayList<DrawCardEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            DrawCardEventEventResponse typedResponse = new DrawCardEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.level = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.cardId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DrawCardEventEventResponse> drawCardEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, DrawCardEventEventResponse>() {
            @Override
            public DrawCardEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(DRAWCARDEVENT_EVENT, log);
                DrawCardEventEventResponse typedResponse = new DrawCardEventEventResponse();
                typedResponse.log = log;
                typedResponse.level = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.cardId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DrawCardEventEventResponse> drawCardEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DRAWCARDEVENT_EVENT));
        return drawCardEventEventFlowable(filter);
    }

    @Deprecated
    public static MarketContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MarketContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MarketContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MarketContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MarketContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MarketContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MarketContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MarketContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MarketContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MarketContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MarketContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MarketContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<MarketContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MarketContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MarketContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MarketContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class DrawCardEventEventResponse {
        public Log log;

        public BigInteger level;

        public BigInteger cardId;
    }
}
