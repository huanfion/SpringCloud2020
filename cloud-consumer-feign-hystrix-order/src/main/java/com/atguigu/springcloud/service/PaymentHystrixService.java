package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/30 21:10
 */
@Service
@FeignClient(value ="CLOUD-PROVIDER-HYSTRIX-PAY" )
public interface PaymentHystrixService {
    @GetMapping("/pay/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);
    @GetMapping("/pay/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
