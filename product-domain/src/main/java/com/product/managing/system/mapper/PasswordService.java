package com.product.managing.system.mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
@Slf4j
public class PasswordService {

    public String hashPassword(String rawPassword) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, 65536, 256);
        byte[] hash;
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            hash = factory.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            log.error("error while hashing password {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return Base64.getEncoder().encodeToString(hash);
    }

    public boolean verifyPassword(String inputPassword, String hashedPassword) throws Exception {
        byte[] salt = new byte[16];
        KeySpec spec = new PBEKeySpec(inputPassword.toCharArray(), salt, 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        String newHash = Base64.getEncoder().encodeToString(hash);

        return newHash.equals(hashedPassword);
    }
}
