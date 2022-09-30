package com.example.diplomaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication()
@EnableJpaRepositories("com.example.diplomaapi.repositories")
public class DiplomaapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiplomaapiApplication.class, args);
    }
}
