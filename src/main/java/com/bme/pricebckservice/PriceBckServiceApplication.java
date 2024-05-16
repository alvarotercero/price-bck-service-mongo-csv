package com.bme.pricebckservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bme.pricebckservice")
@EnableMongoRepositories
@EnableFeignClients
public class PriceBckServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceBckServiceApplication.class, args);
    }

}
