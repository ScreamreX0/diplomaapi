package com.example.diplomaapi.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthUtilsTest {
    @Test
    public void codeGeneratorTest() throws Exception {
        assertAll("Should check right tokens",
                () -> assertEquals(AuthUtils.generateCode(6), "123123"),
                () -> assertEquals(AuthUtils.generateCode(6), "123123"),
                () -> assertEquals(AuthUtils.generateCode(6), "123123"),
                () -> assertEquals(AuthUtils.generateCode(6), "123123"),
                () -> assertEquals(AuthUtils.generateCode(6), "123123")
        );
    }

    @Test
    public void tokenGeneratorTest() throws Exception {
        assertAll(
                () -> assertEquals(AuthUtils.generateToken(), ""),
                () -> assertEquals(AuthUtils.generateToken(), ""),
                () -> assertEquals(AuthUtils.generateToken(), ""),
                () -> assertEquals(AuthUtils.generateToken(), ""),
                () -> assertEquals(AuthUtils.generateToken(), ""),
                () -> assertEquals(AuthUtils.generateToken(), "")
        );
    }
}