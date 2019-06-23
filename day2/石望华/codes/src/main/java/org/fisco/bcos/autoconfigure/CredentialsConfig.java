/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fisco.bcos.autoconfigure;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// 加载用户私钥（创建外部账户的证书并返回）
@Data
@Configuration
@ConfigurationProperties(prefix = "user-key") //  user-key 见src/resources/application.yml
@Slf4j
public class CredentialsConfig {

    private String userKey;
    //    static Logger logger = LoggerFactory.getLogger(CredentialsConfig.class);

    @Bean
    public Credentials getCredentials() throws Exception {
        log.info("userKye : {}", userKey);
        Credentials credentials = GenCredential.create(userKey); // sdk发送交易需要一个外部账户 这里创建一个外部账户
        if (credentials == null) {
            throw new Exception("create Credentials failed");
        }
        return credentials;
    }
}
