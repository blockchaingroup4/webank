package org.fisco.bcos.autoconfigure;

import lombok.Data;
import org.fisco.bcos.beans.TestKey;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "test-keys")
public class TestKeyConfig {
    String user1;
    String user2;
    String user3;
    @Bean
    public TestKey getTestKey(){
        TestKey ret = new TestKey();
        ret.setUser1(user1);
        ret.setUser2(user2);
        ret.setUser3(user3);
        return ret;
    }
}
