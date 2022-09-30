package com.example.diplomaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DiplomaapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiplomaapiApplication.class, args);
    }
}
