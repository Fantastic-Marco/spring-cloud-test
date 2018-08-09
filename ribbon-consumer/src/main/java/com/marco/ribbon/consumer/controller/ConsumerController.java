package com.marco.ribbon.consumer.controller;

import com.marco.ribbon.consumer.service.ConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by landun on 2018/8/8.
 */
@RestController
@RequestMapping("/")
public class ConsumerController {

    @Autowired
    ConsumerService service;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return service.hello();
    }

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health() {
        return service.health();
    }

    @RequestMapping(value = "/add/{number}",method = RequestMethod.GET)
    public Integer add(@PathVariable("number") int number) throws InterruptedException, ExecutionException, TimeoutException {
        return service.add(number);
    }


}
