package com.atguigu.springcloud.service;

import com.atguigu.springcloud.config.FeignClientConfig;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/26 18:57
 */
@Component
@FeignClient(value = "CLOUD-PAY-SERVICE",configuration = FeignClientConfig.class)
public interface PaymentFeignService {
    @GetMapping("/payment/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);
}
