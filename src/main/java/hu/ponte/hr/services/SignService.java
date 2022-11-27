package hu.ponte.hr.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;


@Service
@AllArgsConstructor
public class SignService {
    private PrivateKey privateKey;
    private PublicKey publicKey;


    public String signatureIt(byte[] data) {
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
