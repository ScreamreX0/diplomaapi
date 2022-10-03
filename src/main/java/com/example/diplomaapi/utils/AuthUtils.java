package com.example.diplomaapi.utils;

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
}
