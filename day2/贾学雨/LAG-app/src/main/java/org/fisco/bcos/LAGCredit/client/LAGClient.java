package org.fisco.bcos.LAGCredit.client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fisco.bcos.LAGCredit.contract.LAGCredit;
import org.fisco.bcos.LAGCredit.contract.LAGCredit.TransferEventEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LAGClient {

	static Logger logger = LoggerFactory.getLogger(LAGClient.class);

	private Web3j web3j;

	private Credentials credentials;

	public Web3j getWeb3j() {
		return web3j;
	}

	public void setWeb3j(Web3j web3j) {
		this.web3j = web3j;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public void recordLAGAddr(String address) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty("address", address);
		final Resource contractResource = new ClassPathResource("contract.properties");
		FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
		prop.store(fileOutputStream, "contract address");
	}

	public String loadLAGAddr() throws Exception {
		// load LAG contact address from contract.properties
		Properties prop = new Properties();
		final Resource contractResource = new ClassPathResource("contract.properties");
		prop.load(contractResource.getInputStream());

		String contractAddress = prop.getProperty("address");
		if (contractAddress == null || contractAddress.trim().equals("")) {
			throw new Exception(" load LAG contract address failed, please deploy it first. ");
		}
		logger.info(" load LAG address from contract.properties, address is {}", contractAddress);
		return contractAddress;
	}

	public void initialize() throws Exception {

		// init the Service
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run();

		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		Web3j web3j = Web3j.build(channelEthereumService, 1);

		// init Credentials
		Credentials credentials = Credentials.create(Keys.createEcKeyPair());

		setCredentials(credentials);
		setWeb3j(web3j);

		logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
	}

	private static BigInteger gasPrice = new BigInteger("30000000");
	private static BigInteger gasLimit = new BigInteger("30000000");

	public void deployLAGAndRecordAddr(BigInteger initialSupply, String CreditName, String CreditSymbol) {
		try {
			 LAGCredit lagCredit = LAGCredit.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit),initialSupply, CreditName, CreditSymbol).send();
			System.out.println(" deploy LAG success, contract address is " + lagCredit.getContractAddress());

			recordLAGAddr(lagCredit.getContractAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" deploy LAG contract failed, error message is  " + e.getMessage());
		}
	}

	public void queryLAGAmount(String LAGAccount) {
		try {
			String contractAddress = loadLAGAddr();

			LAGCredit lagCredit = LAGCredit.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			BigInteger result = lagCredit.balanceOf(LAGAccount).send();
			
			System.out.printf(" LAG account %s, value %s \n", LAGAccount, result.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error(" queryLAGAmount exception, error message is {}", e.getMessage());

			System.out.printf(" query LAG account failed, error message is %s\n", e.getMessage());
		}
	}

	public void LAGAccountTotalSupply() {
		try {
			String contractAddress = loadLAGAddr();

			LAGCredit lagCredit = LAGCredit.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			BigInteger result = lagCredit.getTotalSupply().send();
			System.out.printf(" TotalSupply: %s \n",result.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			logger.error(" error message is {}", e.getMessage());
			System.out.printf(" error message is %s\n", e.getMessage());
		}
	}

	public void transferLAG(String toLAGAccount, BigInteger amount) {
		try {
			String contractAddress = loadLAGAddr();
			LAGCredit lagCredit = LAGCredit.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			String owner = lagCredit.owner().send();
			System.out.printf(owner);
			TransactionReceipt receipt = lagCredit.transfer(owner,toLAGAccount, amount).send();
			
			List<TransferEventEventResponse> response = lagCredit.getTransferEventEvents(receipt);
			
			if (!response.isEmpty()) {
				System.out.printf(" transfer success =>  to_LAG: %s, amount: %s \n",
							 toLAGAccount, amount.toString());
				
			} else {
				System.out.println(" event log not found, maybe transaction not exec. ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			logger.error("  error message is {}", e.getMessage());
			System.out.printf("  error message is %s\n", e.getMessage());
		}
	}

	public static void Usage() {
		System.out.println(" Usage:");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.LAG.client.LAGClient deploy");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.LAG.client.LAGClient query account");
		System.out.println(
				"\t java -cp conf/:lib/*:apps/* org.fisco.bcos.LAG.client.LAGClient register account value");
		System.out.println(
				"\t java -cp conf/:lib/*:apps/* org.fisco.bcos.LAG.client.LAGClient transfer from_account to_account amount");
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			Usage();
		}

		LAGClient client = new LAGClient();
		client.initialize();

		switch (args[0]) {
		case "deploy":
			BigInteger initialSupply=new BigInteger(args[1]);
			client.deployLAGAndRecordAddr(initialSupply,args[2],args[3]);
			break;
		case "balanceOf":
			if (args.length < 2) {
				Usage();
			}
			client.queryLAGAmount(args[1]);
			break;
		case "getTotalSupply":
			client.LAGAccountTotalSupply();
			break;

		case "transfer":
			if (args.length < 3) {
				Usage();
			}
			client.transferLAG(args[1], new BigInteger(args[2]));
			break;
		default: {
			Usage();
		}
		}

		System.exit(0);
	}
}
