package hu.ponte.hr.services;

import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;

@Service
public class SignService {
        PrivateKey privateKey;
        PublicKey publicKey;
}
