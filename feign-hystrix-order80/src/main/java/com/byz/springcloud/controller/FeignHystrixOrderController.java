package com.byz.springcloud.controller;

import com.byz.springcloud.service.FeignHystrixOrderService;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "global_fallback")
public class FeignHystrixOrderController {

    @Resource
    private FeignHystrixOrderService feignHystrixOrderService;


    @GetMapping("/consumer/payment/get/ok/{id}")
    public String getFeignHystrixPaymentOk(@PathVariable("id") Integer id) {
        return feignHystrixOrderService.getHystrixPaymentOk(id);
    }

    @GetMapping("/consumer/payment/get/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "feignHystrixPaymentTimeout_fallBack", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
//    })
//    @HystrixCommand
    public String getFeignHystrixPaymentTimeout(@PathVariable("id") Integer id) {
        return feignHystrixOrderService.getHystrixPaymentTimeout(id);
    }
//
//    private String feignHystrixPaymentTimeout_fallBack(Integer id) {
//        return "服务端提供服务太慢了，我等不了了。。。。   id:" + id;
//    }
//
//    private String global_fallback() {
//        return "global 全局fallback方法提醒您，服务提供方发生异常，请稍后重试";
//    }

}
