package com.example.diplomaapi.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthUtilsTest {
    @Test
    public void test() throws Exception {
        assertAll("Should check right tokens",
                () -> assertEquals(AuthUtils.generateCode(6), "123123"),
                () -> assertEquals(AuthUtils.generateCode(6), "123123"),
                () -> assertEquals(AuthUtils.generateCode(6), "123123"),
                () -> assertEquals(AuthUtils.generateCode(6), "123123"),
                () -> assertEquals(AuthUtils.generateCode(6), "123123"));
    }
}