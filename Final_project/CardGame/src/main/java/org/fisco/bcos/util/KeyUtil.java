package org.fisco.bcos.util;

import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.utils.Numeric;

public class KeyUtil {
    static final int PUBLIC_KEY_SIZE = 64;
    static final int PUBLIC_KEY_LENGTH_IN_HEX = PUBLIC_KEY_SIZE << 1;
    public static class UserKey{
        String address;
        String publicKey;
        String privateKey;
        public UserKey(String address, String publicKey, String privateKey){
            this.address = address;
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }
        public String getAddress(){return address;}
        public String getPublicKey(){return publicKey;}
        public String getPrivateKey(){return privateKey;}
    }

    public static UserKey createUserKey(){
        try{
            ECKeyPair keyPair = Keys.createEcKeyPair();
            String publicKey = Numeric.toHexStringWithPrefixZeroPadded(keyPair.getPublicKey(), PUBLIC_KEY_LENGTH_IN_HEX);
            String privateKey = Numeric.toHexStringNoPrefix(keyPair.getPrivateKey());
            String address = "0x" + Keys.getAddress(publicKey);
            return new UserKey(address, publicKey, privateKey);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
