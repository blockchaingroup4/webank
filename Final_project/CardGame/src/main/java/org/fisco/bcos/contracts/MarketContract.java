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
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
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
    public static final String BINARY = "6080604052600060045534801561001557600080fd5b50612de1806100256000396000f3006080604052600436106100c5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063139f9aa9146100ca57806314d87e371461010d578063225cf0a91461013857806336351c7c146101d85780633b1e6bf0146102255780634ecb54b014610268578063567602aa146102c35780635ab8782b146103245780636fd1c67c14610367578063ad2b232f146103ca578063b1e099081461042b578063c2856f5a146104ae578063c2adc11114610505575b600080fd5b3480156100d657600080fd5b5061010b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610572565b005b34801561011957600080fd5b506101226105b6565b6040518082815260200191505060405180910390f35b34801561014457600080fd5b506101d6600480360381019080803590602001908201803590602001919091929391929390803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001919091929391929390803560000b90602001909291905050506105c2565b005b3480156101e457600080fd5b50610223600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610cfc565b005b34801561023157600080fd5b50610266600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610edc565b005b34801561027457600080fd5b506102c1600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001919091929391929390505050610f20565b005b3480156102cf57600080fd5b5061030e600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506112a8565b6040518082815260200191505060405180910390f35b34801561033057600080fd5b50610365600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506114d8565b005b34801561037357600080fd5b506103c8600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061151c565b005b3480156103d657600080fd5b50610415600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050612519565b6040518082815260200191505060405180910390f35b34801561043757600080fd5b506104ac600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506129f6565b005b3480156104ba57600080fd5b506104ef600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050612abe565b6040518082815260200191505060405180910390f35b34801561051157600080fd5b5061053060048036038101908080359060200190929190505050612bb2565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008080549050905090565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166367a26e4686866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b1580156106b357600080fd5b505af11580156106c7573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16636086ce1b8660018060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663159db4858a6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156107c857600080fd5b505af11580156107dc573d6000803e3d6000fd5b505050506040513d60208110156107f257600080fd5b8101908080519060200190929190505050036040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018263ffffffff1663ffffffff16815260200192505050600060405180830381600087803b15801561089557600080fd5b505af11580156108a9573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663499ec0b68888878787878c6040518863ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018560000b60000b81526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183810383528a8a828181526020019250808284378201915050838103825287878281815260200192508082843782019150509950505050505050505050600060405180830381600087803b1580156109f057600080fd5b505af1158015610a04573d6000803e3d6000fd5b505050507f3b051736e69fd8258fc7cbc86ebf329dbde17795d4a62609e8ad40a089e58c2c878783878787600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663320721798c6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b158015610aec57600080fd5b505af1158015610b00573d6000803e3d6000fd5b505050506040513d6020811015610b1657600080fd5b8101908080519060200190929190505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ba96ff118d6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b158015610be457600080fd5b505af1158015610bf8573d6000803e3d6000fd5b505050506040513d6020811015610c0e57600080fd5b81019080805190602001909291905050508d60405180806020018960000b60000b81526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001861515151581526020018581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183810383528c8c828181526020019250808284378201915050838103825288888281815260200192508082843782019150509b50505050505050505050505060405180910390a150505050505050565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece846040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b158015610dbb57600080fd5b505af1158015610dcf573d6000803e3d6000fd5b505050506040513d6020811015610de557600080fd5b81019080805190602001909291905050509050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f6848484016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610ebf57600080fd5b505af1158015610ed3573d6000803e3d6000fd5b50505050505050565b80600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000806000806000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663159db485896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b158015610fe557600080fd5b505af1158015610ff9573d6000803e3d6000fd5b505050506040513d602081101561100f57600080fd5b810190808051906020019092919050505063ffffffff1611151561103257600080fd5b4233600454604051602001808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166c0100000000000000000000000002815260140182815260200193505050506040516020818303038152906040526040518082805190602001908083835b6020831015156110d457805182526020820191506020810190506020830392506110af565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600190048686604051602001808383808284378201915050925050506040516020818303038152906040526040518082805190602001908083835b602083101515611161578051825260208201915060208101905060208303925061113c565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600190040193506001612710858115156111a457fe5b0601925083915060046000815480929190600101919050555060009050611701831115156111d5576001905061122e565b6122b9831115156111e9576002905061122d565b6126a1831115156111fd576003905061122c565b61270583111515611211576004905061122b565b61270f83111515611225576005905061122a565b600690505b5b5b5b5b7f6c8d07f0a1d7e12a3cafd9b47f44fbaa1edae67dfa25f183989b446af1ee04228183604051808360000b60000b81526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a150505050505050565b6000808390806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639380b3b38460016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018215151515815260200192505050600060405180830381600087803b1580156113d957600080fd5b505af11580156113ed573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639d8dd8e084846040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1580156114b657600080fd5b505af11580156114ca573d6000803e3d6000fd5b505050506000905092915050565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000806000806060600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece8a6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156115e457600080fd5b505af11580156115f8573d6000803e3d6000fd5b505050506040513d602081101561160e57600080fd5b81019080805190602001909291905050509650600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ba96ff11896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156116de57600080fd5b505af11580156116f2573d6000803e3d6000fd5b505050506040513d602081101561170857600080fd5b81019080805190602001909291905050509550858710156117c8577fe36b96ea2f71c0a3ceb4458422b6b8f1696995d4886ee42295882661d543147589896001604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390a161250e565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c1551d0d896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561188557600080fd5b505af1158015611899573d6000803e3d6000fd5b505050506040513d60208110156118af57600080fd5b81019080805190602001909291905050509450600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece866040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561197f57600080fd5b505af1158015611993573d6000803e3d6000fd5b505050506040513d60208110156119a957600080fd5b81019080805190602001909291905050509350600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166306ff46a3896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b158015611a7957600080fd5b505af1158015611a8d573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506020811015611ab757600080fd5b810190808051640100000000811115611acf57600080fd5b82810190506020810184811115611ae557600080fd5b8151856001820283011164010000000082111715611b0257600080fd5b50509291905050509250600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166377add29d898b6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015611bfd57600080fd5b505af1158015611c11573d6000803e3d6000fd5b50505050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639380b3b38960006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018215151515815260200192505050600060405180830381600087803b158015611cdf57600080fd5b505af1158015611cf3573d6000803e3d6000fd5b50505050889150600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166307f2d438428a868a8a886040518763ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808781526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825286818151815260200191508051906020019080838360005b83811015611e4b578082015181840152602081019050611e30565b50505050905090810190601f168015611e785780820380516001836020036101000a031916815260200191505b50975050505050505050602060405180830381600087803b158015611e9c57600080fd5b505af1158015611eb0573d6000803e3d6000fd5b505050506040513d6020811015611ec657600080fd5b81019080805190602001909291905050509050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16638c702604868a6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015611fca57600080fd5b505af1158015611fde573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166367a26e468a8a6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b1580156120d357600080fd5b505af11580156120e7573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166384a372738a836040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1580156121b057600080fd5b505af11580156121c4573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166384a3727386836040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15801561228d57600080fd5b505af11580156122a1573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f68a888a036040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15801561236c57600080fd5b505af1158015612380573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f6868887016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15801561244b57600080fd5b505af115801561245f573d6000803e3d6000fd5b5050505061246c88612bf4565b507fe36b96ea2f71c0a3ceb4458422b6b8f1696995d4886ee42295882661d543147589896000604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390a15b505050505050505050565b6000806000806000600a9350600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639b96eece886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156125e257600080fd5b505af11580156125f6573d6000803e3d6000fd5b505050506040513d602081101561260c57600080fd5b810190808051906020019092919050505092508386029150828211156126a9577ffc4becad0718c3d7f3e9abc6016a8704e695d7c9d56b2d449e3caa75aa2e2bf787876001604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828152602001935050505060405180910390a1600194506129ec565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b46310f6888486036040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15801561277057600080fd5b505af1158015612784573d6000803e3d6000fd5b50505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663159db485886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561284557600080fd5b505af1158015612859573d6000803e3d6000fd5b505050506040513d602081101561286f57600080fd5b81019080805190602001909291905050509050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16636086ce1b88888463ffffffff16016040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018263ffffffff1663ffffffff16815260200192505050600060405180830381600087803b15801561295b57600080fd5b505af115801561296f573d6000803e3d6000fd5b505050507ffc4becad0718c3d7f3e9abc6016a8704e695d7c9d56b2d449e3caa75aa2e2bf787876000604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828152602001935050505060405180910390a1600094505b5050505092915050565b82600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639380b3b38360006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018215151515815260200192505050600060405180830381600087803b158015612b8a57600080fd5b505af1158015612b9e573d6000803e3d6000fd5b50505050612bab82612bf4565b9050919050565b60008082815481101515612bc257fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b60008060008091505b600080549050821015612d58578373ffffffffffffffffffffffffffffffffffffffff16600083815481101515612c3057fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415612d4b578190505b600160008054905003811015612d2c57600060018201815481101515612c9d57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600082815481101515612cd757fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508080600101915050612c7b565b6000805480919060019003612d419190612d64565b5060009250612d5d565b8180600101925050612bfd565b600192505b5050919050565b815481835581811115612d8b57818360005260206000209182019101612d8a9190612d90565b5b505050565b612db291905b80821115612dae576000816000905550600101612d96565b5090565b905600a165627a7a7230582006ed0b189861edcf0275d359b4ade2a523e345e9c82818c738f55c2f2b4db1c10029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"setCardManagementInterface\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getCardsOnSaleNum\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"url\",\"type\":\"string\"},{\"name\":\"level\",\"type\":\"int8\"}],\"name\":\"createCardAndGiveTo\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"recharge\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"setTransactionManagementInterface\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"},{\"name\":\"wish\",\"type\":\"string\"}],\"name\":\"drawCard\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"},{\"name\":\"price\",\"type\":\"uint256\"}],\"name\":\"pushCard\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"setAccoundManagementInterface\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"buyer\",\"type\":\"address\"},{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"buyCard\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"},{\"name\":\"times\",\"type\":\"uint256\"}],\"name\":\"buyDrawCards\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"accountAddr\",\"type\":\"address\"},{\"name\":\"cardAddr\",\"type\":\"address\"},{\"name\":\"transactionAddr\",\"type\":\"address\"}],\"name\":\"setACTInterfaces\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"pullCard\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getAddressOfCardOnSale\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"level\",\"type\":\"int8\"},{\"indexed\":false,\"name\":\"cardId\",\"type\":\"address\"}],\"name\":\"DrawCardEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"name\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"level\",\"type\":\"int8\"},{\"indexed\":false,\"name\":\"cardId\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"url\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"isOnSale\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"price\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"CreateCardAndGiveEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"addr\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"times\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"code\",\"type\":\"int256\"}],\"name\":\"BuyDrawCardsEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"buyer\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"cardId\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"code\",\"type\":\"int256\"}],\"name\":\"BuyCardEvent\",\"type\":\"event\"}]";

    public static final String FUNC_SETCARDMANAGEMENTINTERFACE = "setCardManagementInterface";

    public static final String FUNC_GETCARDSONSALENUM = "getCardsOnSaleNum";

    public static final String FUNC_CREATECARDANDGIVETO = "createCardAndGiveTo";

    public static final String FUNC_RECHARGE = "recharge";

    public static final String FUNC_SETTRANSACTIONMANAGEMENTINTERFACE = "setTransactionManagementInterface";

    public static final String FUNC_DRAWCARD = "drawCard";

    public static final String FUNC_PUSHCARD = "pushCard";

    public static final String FUNC_SETACCOUNDMANAGEMENTINTERFACE = "setAccoundManagementInterface";

    public static final String FUNC_BUYCARD = "buyCard";

    public static final String FUNC_BUYDRAWCARDS = "buyDrawCards";

    public static final String FUNC_SETACTINTERFACES = "setACTInterfaces";

    public static final String FUNC_PULLCARD = "pullCard";

    public static final String FUNC_GETADDRESSOFCARDONSALE = "getAddressOfCardOnSale";

    public static final Event DRAWCARDEVENT_EVENT = new Event("DrawCardEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int8>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event CREATECARDANDGIVEEVENT_EVENT = new Event("CreateCardAndGiveEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int8>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event BUYDRAWCARDSEVENT_EVENT = new Event("BuyDrawCardsEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}));
    ;

    public static final Event BUYCARDEVENT_EVENT = new Event("BuyCardEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Int256>() {}));
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
                Arrays.<Type>asList(new Address(addr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setCardManagementInterface(String addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCARDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new Address(addr)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setCardManagementInterfaceSeq(String addr) {
        final Function function = new Function(
                FUNC_SETCARDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new Address(addr)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> getCardsOnSaleNum() {
        final Function function = new Function(FUNC_GETCARDSONSALENUM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> createCardAndGiveTo(String name, String who, String cardId, String url, BigInteger level) {
        final Function function = new Function(
                FUNC_CREATECARDANDGIVETO, 
                Arrays.<Type>asList(new Utf8String(name),
                new Address(who),
                new Address(cardId),
                new Utf8String(url),
                new Int8(level)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createCardAndGiveTo(String name, String who, String cardId, String url, BigInteger level, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATECARDANDGIVETO, 
                Arrays.<Type>asList(new Utf8String(name),
                new Address(who),
                new Address(cardId),
                new Utf8String(url),
                new Int8(level)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createCardAndGiveToSeq(String name, String who, String cardId, String url, BigInteger level) {
        final Function function = new Function(
                FUNC_CREATECARDANDGIVETO, 
                Arrays.<Type>asList(new Utf8String(name),
                new Address(who),
                new Address(cardId),
                new Utf8String(url),
                new Int8(level)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> recharge(String addr, BigInteger amount) {
        final Function function = new Function(
                FUNC_RECHARGE, 
                Arrays.<Type>asList(new Address(addr),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void recharge(String addr, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_RECHARGE, 
                Arrays.<Type>asList(new Address(addr),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String rechargeSeq(String addr, BigInteger amount) {
        final Function function = new Function(
                FUNC_RECHARGE, 
                Arrays.<Type>asList(new Address(addr),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setTransactionManagementInterface(String addr) {
        final Function function = new Function(
                FUNC_SETTRANSACTIONMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new Address(addr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setTransactionManagementInterface(String addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETTRANSACTIONMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new Address(addr)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setTransactionManagementInterfaceSeq(String addr) {
        final Function function = new Function(
                FUNC_SETTRANSACTIONMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new Address(addr)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> drawCard(String who, String wish) {
        final Function function = new Function(
                FUNC_DRAWCARD, 
                Arrays.<Type>asList(new Address(who),
                new Utf8String(wish)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void drawCard(String who, String wish, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_DRAWCARD, 
                Arrays.<Type>asList(new Address(who),
                new Utf8String(wish)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String drawCardSeq(String who, String wish) {
        final Function function = new Function(
                FUNC_DRAWCARD, 
                Arrays.<Type>asList(new Address(who),
                new Utf8String(wish)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> pushCard(String cardId, BigInteger price) {
        final Function function = new Function(
                FUNC_PUSHCARD, 
                Arrays.<Type>asList(new Address(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void pushCard(String cardId, BigInteger price, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PUSHCARD, 
                Arrays.<Type>asList(new Address(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String pushCardSeq(String cardId, BigInteger price) {
        final Function function = new Function(
                FUNC_PUSHCARD, 
                Arrays.<Type>asList(new Address(cardId),
                new Uint256(price)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setAccoundManagementInterface(String addr) {
        final Function function = new Function(
                FUNC_SETACCOUNDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new Address(addr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setAccoundManagementInterface(String addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETACCOUNDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new Address(addr)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setAccoundManagementInterfaceSeq(String addr) {
        final Function function = new Function(
                FUNC_SETACCOUNDMANAGEMENTINTERFACE, 
                Arrays.<Type>asList(new Address(addr)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> buyCard(String buyer, String cardId) {
        final Function function = new Function(
                FUNC_BUYCARD, 
                Arrays.<Type>asList(new Address(buyer),
                new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void buyCard(String buyer, String cardId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_BUYCARD, 
                Arrays.<Type>asList(new Address(buyer),
                new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String buyCardSeq(String buyer, String cardId) {
        final Function function = new Function(
                FUNC_BUYCARD, 
                Arrays.<Type>asList(new Address(buyer),
                new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> buyDrawCards(String addr, BigInteger times) {
        final Function function = new Function(
                FUNC_BUYDRAWCARDS, 
                Arrays.<Type>asList(new Address(addr),
                new Uint256(times)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void buyDrawCards(String addr, BigInteger times, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_BUYDRAWCARDS, 
                Arrays.<Type>asList(new Address(addr),
                new Uint256(times)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String buyDrawCardsSeq(String addr, BigInteger times) {
        final Function function = new Function(
                FUNC_BUYDRAWCARDS, 
                Arrays.<Type>asList(new Address(addr),
                new Uint256(times)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setACTInterfaces(String accountAddr, String cardAddr, String transactionAddr) {
        final Function function = new Function(
                FUNC_SETACTINTERFACES, 
                Arrays.<Type>asList(new Address(accountAddr),
                new Address(cardAddr),
                new Address(transactionAddr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setACTInterfaces(String accountAddr, String cardAddr, String transactionAddr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETACTINTERFACES, 
                Arrays.<Type>asList(new Address(accountAddr),
                new Address(cardAddr),
                new Address(transactionAddr)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setACTInterfacesSeq(String accountAddr, String cardAddr, String transactionAddr) {
        final Function function = new Function(
                FUNC_SETACTINTERFACES, 
                Arrays.<Type>asList(new Address(accountAddr),
                new Address(cardAddr),
                new Address(transactionAddr)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> pullCard(String cardId) {
        final Function function = new Function(
                FUNC_PULLCARD, 
                Arrays.<Type>asList(new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void pullCard(String cardId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PULLCARD, 
                Arrays.<Type>asList(new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String pullCardSeq(String cardId) {
        final Function function = new Function(
                FUNC_PULLCARD, 
                Arrays.<Type>asList(new Address(cardId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> getAddressOfCardOnSale(BigInteger index) {
        final Function function = new Function(FUNC_GETADDRESSOFCARDONSALE, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public List<DrawCardEventEventResponse> getDrawCardEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(DRAWCARDEVENT_EVENT, transactionReceipt);
        ArrayList<DrawCardEventEventResponse> responses = new ArrayList<DrawCardEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            DrawCardEventEventResponse typedResponse = new DrawCardEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.level = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.cardId = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.cardId = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DrawCardEventEventResponse> drawCardEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DRAWCARDEVENT_EVENT));
        return drawCardEventEventFlowable(filter);
    }

    public List<CreateCardAndGiveEventEventResponse> getCreateCardAndGiveEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREATECARDANDGIVEEVENT_EVENT, transactionReceipt);
        ArrayList<CreateCardAndGiveEventEventResponse> responses = new ArrayList<CreateCardAndGiveEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateCardAndGiveEventEventResponse typedResponse = new CreateCardAndGiveEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.level = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.cardId = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.url = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.isOnSale = (Boolean) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.owner = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreateCardAndGiveEventEventResponse> createCardAndGiveEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, CreateCardAndGiveEventEventResponse>() {
            @Override
            public CreateCardAndGiveEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(CREATECARDANDGIVEEVENT_EVENT, log);
                CreateCardAndGiveEventEventResponse typedResponse = new CreateCardAndGiveEventEventResponse();
                typedResponse.log = log;
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.level = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.cardId = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.url = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.isOnSale = (Boolean) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.owner = (String) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreateCardAndGiveEventEventResponse> createCardAndGiveEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATECARDANDGIVEEVENT_EVENT));
        return createCardAndGiveEventEventFlowable(filter);
    }

    public List<BuyDrawCardsEventEventResponse> getBuyDrawCardsEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(BUYDRAWCARDSEVENT_EVENT, transactionReceipt);
        ArrayList<BuyDrawCardsEventEventResponse> responses = new ArrayList<BuyDrawCardsEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BuyDrawCardsEventEventResponse typedResponse = new BuyDrawCardsEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.times = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.code = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BuyDrawCardsEventEventResponse> buyDrawCardsEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, BuyDrawCardsEventEventResponse>() {
            @Override
            public BuyDrawCardsEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(BUYDRAWCARDSEVENT_EVENT, log);
                BuyDrawCardsEventEventResponse typedResponse = new BuyDrawCardsEventEventResponse();
                typedResponse.log = log;
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.times = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.code = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BuyDrawCardsEventEventResponse> buyDrawCardsEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BUYDRAWCARDSEVENT_EVENT));
        return buyDrawCardsEventEventFlowable(filter);
    }

    public List<BuyCardEventEventResponse> getBuyCardEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(BUYCARDEVENT_EVENT, transactionReceipt);
        ArrayList<BuyCardEventEventResponse> responses = new ArrayList<BuyCardEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BuyCardEventEventResponse typedResponse = new BuyCardEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.cardId = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.code = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BuyCardEventEventResponse> buyCardEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, BuyCardEventEventResponse>() {
            @Override
            public BuyCardEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(BUYCARDEVENT_EVENT, log);
                BuyCardEventEventResponse typedResponse = new BuyCardEventEventResponse();
                typedResponse.log = log;
                typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.cardId = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.code = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BuyCardEventEventResponse> buyCardEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BUYCARDEVENT_EVENT));
        return buyCardEventEventFlowable(filter);
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

        public String cardId;
    }

    public static class CreateCardAndGiveEventEventResponse {
        public Log log;

        public String name;

        public BigInteger level;

        public String cardId;

        public String url;

        public Boolean isOnSale;

        public BigInteger price;

        public String owner;
    }

    public static class BuyDrawCardsEventEventResponse {
        public Log log;

        public String addr;

        public BigInteger times;

        public BigInteger code;
    }

    public static class BuyCardEventEventResponse {
        public Log log;

        public String buyer;

        public String cardId;

        public BigInteger code;
    }
}
