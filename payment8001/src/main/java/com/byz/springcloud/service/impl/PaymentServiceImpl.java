package com.byz.springcloud.service.impl;

import com.byz.springcloud.dao.PaymentDao;
import com.byz.springcloud.entities.Payment;
import com.byz.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public Payment getOne(Integer id) {
        return paymentDao.getOne(id);
    }
}
