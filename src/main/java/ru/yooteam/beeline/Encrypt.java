package ru.yooteam.beeline;


import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.jose4j.lang.JoseException;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Encrypt {
    private static final String publicKeyBase64 = "CHANGE ME";
    /**
     * Данные для шифрования
     */
    private static final String data = "CHANGE ME";

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, JoseException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBase64)));
        System.out.println(Cryptos.encrypt(publicKey, data));
    }
}
