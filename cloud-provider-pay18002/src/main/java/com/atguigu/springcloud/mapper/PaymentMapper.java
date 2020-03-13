package com.atguigu.springcloud.mapper;

import com.atguigu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/12 16:20
 */
@Mapper
public interface PaymentMapper {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
