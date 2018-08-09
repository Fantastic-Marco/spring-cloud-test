package com.marco.ribbon.consumer.service;

import com.marco.ribbon.consumer.command.ConsumerCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by landun on 2018/8/9.
 */
@Service
public class ConsumerService {
    @Autowired
    RestTemplate template;

    @HystrixCommand(fallbackMethod = "exceptionHandler", ignoreExceptions = {HystrixBadRequestException.class})
    //单个返回结果
    public String hello() {
        return template.getForEntity("http://USER-SERVICE/hello", String.class).getBody();
    }

    public String health() {
        return template.getForEntity("http://USER-SERVICE/actuator/check/health", String.class).getBody();
    }

    //降级处理
    @HystrixCommand(fallbackMethod = "secondServiceExceptionHandler")
    public String exceptionHandler(Throwable throwable) {
        throwable.printStackTrace();
        //启用第二级服务
        return "降级";
    }

    @CacheResult
    @HystrixCommand(fallbackMethod = "addExceptionHandler",commandProperties = {
            @HystrixProperty(name = "requestCache.enabled",value = "true")
    })
    public Integer add(@CacheKey int number) throws InterruptedException, ExecutionException, TimeoutException {
        System.out.println("number " + number + " add");
        return template.getForEntity("http://USER-SERVICE/add/{1}", Integer.class,number).getBody();
    }

    public String secondServiceExceptionHandler(Throwable throwable) {
        return "错误";
    }

    public Integer addExceptionHandler(int number,Throwable throwable) {
        throwable.printStackTrace();
        System.out.println(number + "add failed");
        return -1;
    }

    private String getCacheKey(int number){
        return String.valueOf(number);
    }
}
