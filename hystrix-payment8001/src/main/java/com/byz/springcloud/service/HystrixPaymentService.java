package com.byz.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Component
public class HystrixPaymentService {

    public String paymentInfo_ok(Integer id) {
        return Thread.currentThread().getName() + ": 执行PaymentInfo_ok方法处理业务, id:" + id;
    }


    /**
     * @HystrixCommand 服务降级配置
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentTimeout_fallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_timeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + ": 执行paymentInfo_timeout方法处理业务, id:" + id;
    }

    private String paymentTimeout_fallback(Integer id) {
        return Thread.currentThread().getName() + "  服务器繁忙，请稍后再试，请求id：" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") //失败率达到多少后熔断服务
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id <= 0) {
            throw new RuntimeException("paymentCircuitBreaker,参数不可为负数，id:" + id);
        }
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return Thread.currentThread().getName() + ": 执行paymentCircuitBreaker方法处理业务, id:" + id;
    }


    private String paymentCircuitBreaker_fallback(Integer id) {
        return "paymentCircuitBreaker_fallback, 服务熔断，id:" + id;
    }



}
