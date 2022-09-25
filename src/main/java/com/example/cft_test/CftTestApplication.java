package com.example.cft_test;

import com.example.cft_test.model.Product;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRedisDocumentRepositories(basePackages = "com.example.cft_test.*")
@SpringBootApplication
public class CftTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CftTestApplication.class, args);
    }
}