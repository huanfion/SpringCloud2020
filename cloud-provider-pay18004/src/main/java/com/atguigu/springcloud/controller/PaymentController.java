package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/12 17:36
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;



    @GetMapping("/zk")
    public String paymentzk(){
        return "springcloud with zookeeper:"+port+"\t"+ UUID.randomUUID().toString();
    }

}
