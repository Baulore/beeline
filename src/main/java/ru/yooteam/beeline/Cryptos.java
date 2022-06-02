package ru.yooteam.beeline;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.lang.JoseException;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Cryptos {
    public static String decrypt(PrivateKey privateKey, String data) throws Exception {
        JsonWebEncryption receiverJwe = new JsonWebEncryption();
        // Set the algorithm constraints based on what is agreed upon or expected from the sender
        AlgorithmConstraints algConstraints = new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, KeyManagementAlgorithmIdentifiers.RSA_OAEP);
        receiverJwe.setAlgorithmConstraints(algConstraints);
        AlgorithmConstraints encConstraints = new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
        receiverJwe.setContentEncryptionAlgorithmConstraints(encConstraints);


        // Set the compact serialization on new Json Web Encryption object
        receiverJwe.setCompactSerialization(data);

        // Symmetric encryption, like we are doing here, requires that both parties have the same key.
        // The key will have had to have been securely exchanged out-of-band somehow.
        receiverJwe.setKey(privateKey);

        // Get the message that was encrypted in the JWE. This step performs the actual decryption steps.

        // And do whatever you need to do with the clear text message.
        return receiverJwe.getPlaintextString();
    }

    public static String encrypt(PublicKey publicKey, String data) throws JoseException {
        JsonWebEncryption senderJwe = new JsonWebEncryption();
        senderJwe.setPlaintext(data);
        senderJwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
        senderJwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
        senderJwe.setKey(publicKey);
        return senderJwe.getCompactSerialization();
    }
}
