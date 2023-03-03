package com.buba.springcloud.controller;

import com.buba.springcloud.pojo.CommonResult;
import com.buba.springcloud.pojo.Payment;
import com.buba.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //从yml文件获取端口
    @Value("${server.port}")
    private String port;

    //PostMapping一般都是实体，GetMapping单个参数
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        log.info("***************插入成功*******" + i);
        if (i > 0) {
            return new CommonResult(200, "插入数据库成功" + port, i);
        } else {
            return new CommonResult(444, "插入数据库失败" + port, null);
        }
    }

    //两种写法，一个直接写在url上，一个是写在参数上
//    @GetMapping("/payment/get")
//    public CommonResult queryById(@RequestParam("id") Long id) {
    @GetMapping("/payment/get/{id}")
    public CommonResult queryById(@PathVariable("id") Long id) {
        Payment payment = paymentService.queryById(id);
        log.info("查询成功：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功" + port, payment);
        } else {
            return new CommonResult(444, "查询失败" + port, null);
        }
    }

}