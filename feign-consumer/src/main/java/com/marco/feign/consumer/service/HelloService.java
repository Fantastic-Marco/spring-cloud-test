package com.marco.feign.consumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by landun on 2018/8/9.
 */
@FeignClient(value = "user-service")
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "add/{number}", method = RequestMethod.GET)
    Integer add(@PathVariable("number") int number);
}
