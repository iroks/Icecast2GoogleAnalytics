package com.coherentreceiver.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableZuulProxy
@SpringBootApplication
@EnableScheduling
@EnableAsync

public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
