package com.byz.springcloud.dao;

import com.byz.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    Payment getOne(@Param("id") Integer id);


}
