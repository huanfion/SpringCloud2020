package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/12 17:36
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result>0){
            return  new CommonResult(200,"插入数据库成功");
        }
        else{
            return new CommonResult(444,"插入数据库失败");
        }
    }

    @GetMapping("/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        if (paymentById!=null){
            return  new CommonResult(200,"查询成功",paymentById);
        }else{
            return  new CommonResult(444,"没有记录，查询ID"+id,null);
        }
    }
}