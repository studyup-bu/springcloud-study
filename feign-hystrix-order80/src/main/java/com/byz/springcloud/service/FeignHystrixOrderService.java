package com.byz.springcloud.service;

import com.byz.springcloud.fallback.FeignHystrixOrderFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(value = "HYSTRIX-PAYMENT",fallback = FeignHystrixOrderFallBackService.class)
public interface FeignHystrixOrderService {

    @GetMapping("/hystrix/payment/ok/{id}")
    String getHystrixPaymentOk(@PathVariable("id") Integer id);

    @GetMapping("/hystrix/payment/timeout/{id}")
    String getHystrixPaymentTimeout(@PathVariable("id") Integer id);

}
