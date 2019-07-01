package org.fisco.bcos.util;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
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

    public static String getPrivateKey(Credentials credentials){
        return Numeric.toHexStringNoPrefix(credentials.getEcKeyPair().getPrivateKey());
    }

    public static String getPublicKey(Credentials credentials){
        return getPublicKey(getPrivateKey(credentials));
    }

    public static String getAddress(Credentials credentials){
        return getAddress(getPrivateKey(credentials));
    }

    public static String getPublicKey(String privateKey){
        Credentials credentials = GenCredential.create(privateKey);
        return Numeric.toHexStringWithPrefixZeroPadded(credentials.getEcKeyPair().getPublicKey(), PUBLIC_KEY_LENGTH_IN_HEX);
    }

    public static String getAddress(String privateKey){
        return Keys.getAddress(getPublicKey(privateKey));
    }
}
