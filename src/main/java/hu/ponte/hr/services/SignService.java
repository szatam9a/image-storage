package hu.ponte.hr.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;


@Service
@AllArgsConstructor
public class SignService {
    private PrivateKey privateKey;
    private PublicKey publicKey;


    public String encrypt(byte[] data) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(data);
            byte[] signature = sign.sign();
            return encode(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
