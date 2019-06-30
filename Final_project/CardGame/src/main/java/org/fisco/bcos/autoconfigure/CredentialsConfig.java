package org.fisco.bcos.autoconfigure;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.beans.TestKey;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "user-key")
public class CredentialsConfig {

    private String userKey;

    @Bean
    public Credentials getCredentials() throws Exception {
        log.info("userKye : {}", userKey);
        Credentials credentials = GenCredential.create(userKey);
        if (credentials == null) {
            throw new Exception("create Credentials failed");
        }
        return credentials;
    }
}