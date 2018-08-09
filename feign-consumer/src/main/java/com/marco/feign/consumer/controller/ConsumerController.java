package com.marco.feign.consumer.controller;

import com.marco.feign.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by landun on 2018/8/9.
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return helloService.hello();
    }

    @RequestMapping(value = "/add/{number}",method = RequestMethod.GET)
    public Integer add(@PathVariable("number") int number){
        return helloService.add(number);
    }

}
