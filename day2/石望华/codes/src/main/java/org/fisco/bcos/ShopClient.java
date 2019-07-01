package org.fisco.bcos;

import java.math.BigInteger;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contract.Shop;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopClient {

    static Logger logger = LoggerFactory.getLogger(ShopClient.class); //  沿用spring中的日志记录方法

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

    public ShopClient(Web3j web3j, Credentials credentials) {
        this.credentials = credentials;
        this.web3j = web3j;
    }

    //    spring 里初始化web3j对象的方式
    //    public void initialize() throws Exception {
    //
    //        //获取Web3j对象
    //        Web3j web3j = Web3j.build(channelEthereumService, 1);  // 1   service.getGroupId()
    //
    //        // init Credentials
    //        Credentials credentials = Credentials.create(Keys.createEcKeyPair());
    //
    //        setCredentials(credentials);
    //        setWeb3j(web3j);
    //
    //        logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
    //        // 设置交易超时时间为100000毫秒，即100秒
    //        //channelEthereumService.setTimeout(100000);
    //    }

    //    积分合约部署，完成积分初始化
    public Shop deploy() {
        Shop lagCredit = null;
        try {
            lagCredit =
                    Shop.deploy(
                                    web3j,
                                    credentials,
                                    new StaticGasProvider(
                                            GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT),
                                    new BigInteger("200000"),
                                    new BigInteger("100000"),
                                    "LAGC",
                                    "LAG")
                            .send(); //  本来只有6个参数，但没有接受6个参数的deploy函数，遂加了个200000当做initialWeiValue
            logger.info("LAGC address is {}", lagCredit.getContractAddress());
            ;
            return lagCredit;
        } catch (Exception e) {
            logger.error("deploy lacg contract fail: {}", e.getMessage());
        }
        return lagCredit;
    }

    public Shop load(String creditAddress) {
        Shop lagCredit =
                Shop.load(
                        creditAddress,
                        web3j,
                        credentials,
                        new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        return lagCredit;
    }

    public boolean transfer(String creditAddress, String to, BigInteger value) {
        try {
            Shop lagCredit = load(creditAddress);
            TransactionReceipt receipt = lagCredit.transfer(creditAddress, to, value).send(); //
            logger.info("status : {}", receipt.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public long getBalanceByOwner(String creditAddress, String owner) throws Exception {
        Shop lagCredit = load(creditAddress);
        BigInteger balance = lagCredit.balanceOf(owner).send();
        return balance.longValue();
    }

    public long getTotalSupply(String creditAddress) throws Exception {
        Shop lagCredit = load(creditAddress);
        BigInteger total = lagCredit.getTotalSupply().send();
        return total.longValue();
    }
}
