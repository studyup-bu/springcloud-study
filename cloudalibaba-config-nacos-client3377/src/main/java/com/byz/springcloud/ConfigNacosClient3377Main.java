package com.byz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * nacos支持的三种模式
 * 1、单机模式，用于测试和单机试用（nacos数据持久化，仅该模式支持，只支持MySQL）
 * 2、集群模式，用于生产环境，保证高可用
 * 3、多集群模式，用于多数据中心场景
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigNacosClient3377Main {
    public static void main(String[] args) {
        SpringApplication.run(ConfigNacosClient3377Main.class,args);
    }
}
