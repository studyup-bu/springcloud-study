package com.byz.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerOrderController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String paymentUrl;

    @GetMapping("/consumer/order/{id}")
    public String getServerPort(@PathVariable("id") Integer id) {
        //consumer调用payment服务
        String result = restTemplate.getForObject(paymentUrl + "/payment/nacos/" + id, String.class);
        return "consumer order , serverport: " + serverPort + "\t id: " + id + "\t result:" + result;
    }


}
