package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/27 12:20
 */
@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
        return "线程"+Thread.currentThread().getName()+"paymentInfo_OK 正常运行,id"+id;
    }
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandle",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "6000")})
    public String paymentInfo_TimeOut(Integer id){
//        int age=10/0;
        int timenumber=5;
        try {
            TimeUnit.SECONDS.sleep(timenumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id"+id+"耗时（秒）"+timenumber;
    }

    public String paymentInfo_timeoutHandle(Integer id){
        return  "线程"+Thread.currentThread().getName()+"调用服务降级方法";
    }

    //Hystrix 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启熔断器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期,10秒后下一个请求尝试关闭熔断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id<0){
            throw new RuntimeException("*************id不能为负数");
        }
        UUID uuid = UUID.randomUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功！流水号："+uuid;
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数，请稍后再试！...............id:"+id;
    }
}
