package com.example.diplomaapi.utils;

import org.springframework.http.ResponseEntity;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;

public class AuthUtils {
    public static String generateCode(int signsCount) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < signsCount; i++) {
            stringBuilder.append(random.nextInt(10));
        }

        return stringBuilder.toString();
    }

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
