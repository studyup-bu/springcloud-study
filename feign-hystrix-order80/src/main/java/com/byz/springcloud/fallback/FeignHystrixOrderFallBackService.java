package com.byz.springcloud.fallback;

import com.byz.springcloud.service.FeignHystrixOrderService;
import org.springframework.stereotype.Component;

@Component
public class FeignHystrixOrderFallBackService implements FeignHystrixOrderService {
    @Override
    public String getHystrixPaymentOk(Integer id) {
        return "getHystrixPaymentOk 调用异常， id:" + id;
    }

    @Override
    public String getHystrixPaymentTimeout(Integer id) {
        return "getHystrixPaymentTimeout 调用异常，id:" + id;
    }
}
