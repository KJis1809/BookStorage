package com.example.bookstorage.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com/example/bookstorage/repo"})
@EntityScan(basePackages = {"com/example/bookstorage/entity"})
@ComponentScan(basePackages = {
        "com/example/bookstorage/entity",
        "com/example/bookstorage/mapper",
        "com/example/bookstorage/service"
})
public class TestConfig {
}