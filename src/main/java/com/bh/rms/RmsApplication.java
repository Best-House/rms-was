package com.bh.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class RmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RmsApplication.class, args);
    }

}
