package org.fisco.bcos;

import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.utils.Numeric;

public class UserKeyUtils {
    static final int PUBLIC_KEY_SIZE = 64;
    static final int PUBLIC_KEY_LENGTH_IN_HEX = PUBLIC_KEY_SIZE << 1;
    public static class UserKeys{
        String publicKey;
        String privateKey;
        String address;
        public UserKeys(String publicKey, String privateKey, String address){
            this.publicKey = publicKey;
            this.privateKey = privateKey;
            this.address = address;
        }
    }
    public static UserKeys createUserKey(){
        try{
            ECKeyPair keyPair = Keys.createEcKeyPair();
            String publicKey = Numeric.toHexStringWithPrefixZeroPadded(keyPair.getPublicKey(), PUBLIC_KEY_LENGTH_IN_HEX);
            String privateKey = Numeric.toHexStringNoPrefix(keyPair.getPrivateKey());
            String address = "0x" + Keys.getAddress(publicKey);
            return new UserKeys(publicKey,privateKey,address);
        }catch (Exception e){
            return null;
        }
    }
}
