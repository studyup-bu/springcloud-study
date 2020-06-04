package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient   //服务发现nacos加载
public class NacosProviderPayment9002Main {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderPayment9002Main.class,args);
    }
}
