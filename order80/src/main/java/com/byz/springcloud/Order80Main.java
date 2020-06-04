package com.byz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务消费者 80 主启动类
 */
@SpringBootApplication
@EnableEurekaClient
public class Order80Main {
    public static void main(String[] args) {
        SpringApplication.run(Order80Main.class,args);
    }
}
