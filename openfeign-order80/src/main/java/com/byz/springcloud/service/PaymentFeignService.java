package com.byz.springcloud.service;

import com.byz.springcloud.entities.CommonResult;
import com.byz.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "PAYMENT-SERVER")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/{id}")
    CommonResult getPaymentForFeign(@PathVariable("id") Integer id);


}
