package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.Payment;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/12 16:45
 */
public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
