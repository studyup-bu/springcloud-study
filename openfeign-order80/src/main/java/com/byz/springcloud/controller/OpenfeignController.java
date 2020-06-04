package com.byz.springcloud.controller;

import com.byz.springcloud.entities.CommonResult;
import com.byz.springcloud.entities.Payment;
import com.byz.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/openfeign/order")
public class OpenfeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/get/{id}")
    public CommonResult<Payment> getPaymentForFeign(@PathVariable("id") Integer id) {
        return paymentFeignService.getPaymentForFeign(id);
    }

}
