package hu.ponte.hr.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;


@Service
@AllArgsConstructor
public class SignService {
    private PrivateKey privateKey;
    private PublicKey publicKey;


    public String encrypt(byte[] data) {
        try {

            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(data);
            return encode(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public String decrypt(String encryptedMessage) {

        try {
            byte[] encryptedBytes = decode(encryptedMessage);
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
            return new String(decryptedMessage, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
