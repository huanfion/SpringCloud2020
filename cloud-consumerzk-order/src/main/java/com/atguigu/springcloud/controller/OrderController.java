package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/17 9:39
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    private static final String PAYMENT_URL = "http://cloud-pay-service";
    @GetMapping("/zk/pay")
    public String getpayment(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zk",String.class);
    }
}
