package com.marco.feign.consumer.controller;

import com.marco.feign.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by landun on 2018/8/9.
 */
@RefreshScope
@RestController
public class ConsumerController {

    @Autowired
    private UserService helloService;

    @Value("${hello}")
    private String word;

    @Autowired
    Environment env;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return helloService.hello();
    }

    @RequestMapping(value = "/add/{number}", method = RequestMethod.GET)
    public Integer add(@PathVariable("number") int number) {
        return helloService.add(number);
    }

    @RequestMapping(value = "/sayHi",method = RequestMethod.GET)
    public String sayHi(){
        return word;
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello() {
        String s = env.getProperty("hello");
        System.out.println("get properties->" + s);
        return s;
    }

}
