package com.example.ezjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableCaching
@EnableRedisRepositories(basePackages = "com.example.ezjob.persistense.repository.redis")
@EnableJpaRepositories(basePackages = "com.example.ezjob.persistense.repository.jpa")
public class EzJobApplication {
  public static void main(String[] args) {
    SpringApplication.run(com.example.ezjob.EzJobApplication.class, args);
  }
}
