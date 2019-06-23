package org.fisco.bcos;

import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.utils.Numeric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// UserKey生成
public class UserKeyUtil {
    static Logger logger = LoggerFactory.getLogger(UserKeyUtil.class);

    static final int PUBLIC_KEY_SIZE = 64;
    static final int PUBLIC_KEY_LENGTH_IN_HEX = PUBLIC_KEY_SIZE << 1;

    public static void createUserKey() {
        try {
            ECKeyPair keyPair = Keys.createEcKeyPair();
            String publicKey =
                    Numeric.toHexStringWithPrefixZeroPadded(
                            keyPair.getPublicKey(), PUBLIC_KEY_LENGTH_IN_HEX);
            String privateKey = Numeric.toHexStringNoPrefix(keyPair.getPrivateKey());
            String address = "0x" + Keys.getAddress(publicKey);

            logger.info("public key : {}", publicKey);
            logger.info("private key: {}", privateKey);
            logger.info("address : {}", address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createUserKey();
    }
}
