package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/30 21:09
 */
@RestController
@RequestMapping("/order")
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_Global_FallbakcMethod")
public class OrderController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/hystrix/ok/{id}")
    public String getPaymentOk(@PathVariable("id") Integer id) {

        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandle",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    @HystrixCommand
    public String getPaymentTimeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
    public String paymentInfo_timeoutHandle(Integer id){
        return  "消费者端：线程"+Thread.currentThread().getName()+"调用服务降级方法";
    }

    public String paymentInfo_Global_FallbakcMethod(){
        return "消费者端：全局服务降级方法";
    }
}
