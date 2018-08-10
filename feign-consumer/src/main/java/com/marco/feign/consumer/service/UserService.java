package com.marco.feign.consumer.service;

import com.marco.feign.consumer.service.fallback.UserServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by landun on 2018/8/9.
 */
@FeignClient(value = "user-service",fallback = UserServiceFallback.class)
public interface UserService {

    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "add/{number}", method = RequestMethod.GET)
    Integer add(@PathVariable("number") int number);
}
