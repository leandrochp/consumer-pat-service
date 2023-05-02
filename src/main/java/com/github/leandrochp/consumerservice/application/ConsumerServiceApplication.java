package com.github.leandrochp.consumerservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.github.leandrochp.consumerservice.infrastructure.repositories.entities"})
@EnableJpaRepositories(basePackages = {"com.github.leandrochp.consumerservice.infrastructure.repositories.jpas"})
@ComponentScan(basePackages = {"com.github.leandrochp.consumerservice"})
public class ConsumerServiceApplication {

    public static void main(String... args) {
        SpringApplication.run(ConsumerServiceApplication.class, args);
    }

}
