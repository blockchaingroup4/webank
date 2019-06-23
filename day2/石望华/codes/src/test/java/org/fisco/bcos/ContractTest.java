package org.fisco.bcos;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
//           测试这个类 Client可以改为Service
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contract.Shop;
import org.fisco.bcos.temp.HelloWorld;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ContractTest extends BaseTest {

    @Autowired private Web3j web3j;
    @Autowired private Credentials credentials;
    @Autowired private ShopClient lagCreditService;

    String ownerAddress =
            "72a615700ceb58b401dac36449935f22e21c754abfec3fae387007c569cd19d7"; //  店主地址
    String creditAddress = "e6727afedb2eaf00c9e030d520135a44441a9f2a";
    String toAddress = "4b0897b0513fdc7c541b6d9d7e929c4e5364d2db";

    // 部署和调用合约测试
    @Test
    public void deployAndCallHelloWorld() throws Exception {
        // deploy contract
        HelloWorld helloWorld =
                HelloWorld.deploy(
                                web3j,
                                credentials,
                                new StaticGasProvider(
                                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT))
                        .send();
        if (helloWorld != null) { //  说明部署成功
            System.out.println(
                    "HelloWorld address is: " + helloWorld.getContractAddress()); //  输出合约地址
            // call set function
            helloWorld.set("Hello, World!").send();
            // call get function
            String result = helloWorld.get().send();
            System.out.println(result);
            assertTrue("Hello, World!".equals(result));
        }
    }

    @Test
    public void testdeploy() {
        Shop lagCredit = lagCreditService.deploy();
        //        log.info("LAGCredit address : {}",lagCredit.getContractaAddress());
    }

    @Test
    public void testLoad() {
        Shop lagCredit = lagCreditService.load(creditAddress);
        //        log.info("LAGCredit address : {}", lagCredit.getContractaAddress());
    }

    @Test
    public void testTransfer() {
        boolean flag = lagCreditService.transfer(creditAddress, toAddress, new BigInteger("100"));
        if (flag) {
            log.info("transfer success !");
        } else {
            log.info("transfer failed !");
        }
    }

    //    getBalanceByOwner会抛出异常
    @Test
    public void testGetBalanceByOwner() {
        try {
            long balance = lagCreditService.getBalanceByOwner(creditAddress, ownerAddress);
            log.info("Balance of Owner : {}", balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    getTotalSupply会抛出异常
    @Test
    public void testGetTotalSupply() {
        try {
            long totalSupply = lagCreditService.getTotalSupply(creditAddress);
            log.info("Total Supply : {}", totalSupply);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
