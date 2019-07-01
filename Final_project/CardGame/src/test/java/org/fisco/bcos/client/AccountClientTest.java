package org.fisco.bcos.client;

import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.BaseTest;
import org.fisco.bcos.beans.AccountInfo;
import org.fisco.bcos.beans.ContractAddr;
import org.fisco.bcos.clients.AccountContractClient;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contracts.AccountManagementContract;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class AccountClientTest extends BaseTest {
    @Autowired
    Web3j web3j;
    @Autowired
    Credentials credentials;
    @Autowired
    ContractAddr contractAddr;

    //pass
    @Ignore
    @Test
    public void testConstructor(){
        AccountContractClient client = new AccountContractClient(credentials, contractAddr.getAccountContractAddress(), web3j);
        assert(client.getContract() != null);
    }

    //pass
    @Ignore
    @Test
    public void testAddCountAndGetAccountInfo(){
        AccountContractClient client = new AccountContractClient(credentials, contractAddr.getAccountContractAddress(), web3j);
        assert(client.getContract() != null);
        client.addAccount("hello world");
        AccountInfo info = client.getAccountInfo();
        assert(info.getName().equals("hello world"));
        log.info("name: {}", info.getName());
    }
}
