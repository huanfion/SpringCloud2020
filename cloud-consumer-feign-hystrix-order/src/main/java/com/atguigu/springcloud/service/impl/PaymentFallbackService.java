package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentHystrixService {
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService---paymentInfo_OK---fallback";
    }

    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService---paymentInfo_TimeOut---fallback";
    }
}
