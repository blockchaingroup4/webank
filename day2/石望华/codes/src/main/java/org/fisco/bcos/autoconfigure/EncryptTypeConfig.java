package org.fisco.bcos.autoconfigure;

import org.fisco.bcos.web3j.crypto.EncryptType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 加载加密方式
@Configuration
@ConfigurationProperties(prefix = "encrypt-type")
public class EncryptTypeConfig {
    private int encryptType; //  加密类型

    @Bean
    public EncryptType getEncryptType() {
        return new EncryptType(encryptType);
    }

    public void setEncryptType(int encryptType) {
        this.encryptType = encryptType;
    }
}
