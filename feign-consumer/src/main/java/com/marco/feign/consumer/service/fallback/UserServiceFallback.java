package com.marco.feign.consumer.service.fallback;

import com.marco.feign.consumer.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by landun on 2018/8/9.
 */
@Component
public class UserServiceFallback implements UserService{
    @Override
    public String hello() {
        return "fuck you";
    }

    @Override
    public Integer add(@PathVariable("number") int number) {
        return -1;
    }
}
