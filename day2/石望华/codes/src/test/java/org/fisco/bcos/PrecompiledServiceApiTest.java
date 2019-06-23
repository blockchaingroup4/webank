package org.fisco.bcos;

import static org.junit.Assert.assertTrue;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.precompile.config.SystemConfigSerivce;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PrecompiledServiceApiTest extends BaseTest {

    @Autowired Web3j web3j; //   注入服务

    @Autowired private Credentials credentials;

    @Test
    public void testSystemConfigService() throws Exception {
        SystemConfigSerivce systemConfigSerivce = new SystemConfigSerivce(web3j, credentials);
        systemConfigSerivce.setValueByKey("tx_count_limit", "2000"); //  设置tx_count_limit为2000
        //   setValueByKey 是Web3SDK API 中的Precompiled Service API 中的SystemConfigService中的一种API
        String value =
                web3j.getSystemConfigByKey("tx_count_limit")
                        .send()
                        .getSystemConfigByKey(); //  两次调用getSystemConfigByKey
        System.out.println(value);
        assertTrue("2000".equals(value));
    }
}
