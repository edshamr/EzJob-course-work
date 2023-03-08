package com.example.ezjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EzJobApplication {
  public static void main(String[] args) {
    SpringApplication.run(com.example.ezjob.EzJobApplication.class, args);
  }
}
