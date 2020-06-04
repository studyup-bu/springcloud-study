package com.byz.springcloud.controller;

import com.byz.springcloud.entities.CommonResult;
import com.byz.springcloud.entities.Payment;
import com.byz.springcloud.service.HystrixPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HystrixPaymentController {

    @Resource
    private HystrixPaymentService hystrixPaymentService;

    @GetMapping("/hystrix/payment/ok/{id}")
    public String getHystrixPaymentOk(@PathVariable("id") Integer id) {
        String paymentInfo_ok = hystrixPaymentService.paymentInfo_ok(id);
        log.info("getHystrixPaymentOk, result:{}",paymentInfo_ok);
        return paymentInfo_ok;
    }

    @GetMapping("/hystrix/payment/timeout/{id}")
    public String getHystrixPaymentTimeout(@PathVariable("id") Integer id) {
        String paymentInfo_timeout = hystrixPaymentService.paymentInfo_timeout(id);
        log.info("getHystrixPaymentTimeout, result:{}",paymentInfo_timeout);
        return paymentInfo_timeout;
    }

    @GetMapping("/payment/circuitbreaker/get/{id}")
    public String getPaymentCircuitBreaker(@PathVariable("id") Integer id) {
        return hystrixPaymentService.paymentCircuitBreaker(id);
    }

}
