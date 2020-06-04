package com.byz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class ConsumerHystrixDashboard9001Main {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixDashboard9001Main.class,args);
    }
}
