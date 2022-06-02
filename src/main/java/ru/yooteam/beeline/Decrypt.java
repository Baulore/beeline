package ru.yooteam.beeline;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Decrypt {
    private static final String privateKeyBase64 = "CHANGE ME";
    /**
     * Зашифрованные данные
     */
    private static final String data = "CHANGE ME";

    public static void main(String[] args) throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyBase64)));
        System.out.println(Cryptos.decrypt(privateKey, data));
    }
}
