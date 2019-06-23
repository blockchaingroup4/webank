package org.fisco.bcos;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

// 测试链是否联通
@Slf4j
public class Web3jApiTest extends BaseTest {
    // Application类初始化了web3j对象，在业务代码需要的地方可用注解的方式直接使用
    @Autowired Web3j web3j;

    @Test
    public void getBlockNumber() throws IOException {
        BigInteger blockNumber = web3j.getBlockNumber().send().getBlockNumber();
        log.info("blockNumber is {}", blockNumber);
        assertTrue(blockNumber.compareTo(new BigInteger("0")) >= 0);
    }
}
