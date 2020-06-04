package com.byz.springcloud.controller;

import com.byz.springcloud.entities.CommonResult;
import com.byz.springcloud.entities.Payment;
import com.byz.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/{id}")
    public CommonResult getOne(@PathVariable Integer id) {
        Payment payment = paymentService.getOne(id);
        if (ObjectUtils.isEmpty(payment)) {
            return new CommonResult(999,"无此记录",null);
        }
        return new CommonResult(200,"查询成功","请求端口：" + serverPort + "，请求结果：" + payment);
    }


}
