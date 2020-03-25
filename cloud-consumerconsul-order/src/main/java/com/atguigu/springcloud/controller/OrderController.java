package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/25 9:31
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String PAYMENT_URL = "http://consul-provider-pay";
    @GetMapping("/consul/pay")
    public String getpayment(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
    }
}
