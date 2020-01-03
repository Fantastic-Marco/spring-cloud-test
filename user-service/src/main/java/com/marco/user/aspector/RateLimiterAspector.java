/**
 * Copyright (C), 2016-2019, 图匠数据
 * FileName: RateLimiterAspector
 * Author:   Marco
 * Date:     2019/8/27 14:17
 * Description: 流量过滤器
 * History:
 */
package com.marco.user.aspector;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈一句话功能简述〉<br>
 * 〈流量过滤器〉
 *
 * @author Marco
 * @create 2019/8/27
 * @since 1.0.0
 */
@Aspect
@Configuration
public class RateLimiterAspector {
    private final Logger logger = Logger.getLogger(getClass());
    private int MAX_MISSION = 10;
    private AtomicInteger rateLimiter = new AtomicInteger(0);

    @Around("execution(* com.marco.user.controller.DemoController.*(..))")
    public String around(ProceedingJoinPoint joinPoint){
        int count = rateLimiter.incrementAndGet();
        logger.info("已被使用令牌数 {"+count+"}，总令牌数 {"+MAX_MISSION+"}");
        boolean flag = count > MAX_MISSION;
        try {
            if (flag) {
                logger.info("已无多余的令牌");
                return "业务繁忙";
            } else {
                logger.info("领取令牌成功");
                int seed = new Random().nextInt(4);
                TimeUnit.SECONDS.sleep(seed);
                rateLimiter.incrementAndGet();
                return JSON.toJSONString(joinPoint.proceed());
            }
        }catch (Throwable e){
            logger.error(e.getMessage(),e);
            return e.getMessage();
        }finally {
            rateLimiter.decrementAndGet();
            logger.info("归还令牌");
        }
    }

    @AfterThrowing("execution(* com.marco.user.controller.DemoController.*(..))")
    public void exceptionHandler(){
        logger.error("图片下载异常");
        rateLimiter.decrementAndGet();
    }

    @After("execution(* com.marco.user.controller.DemoController.*(..))")
    public void after(){

    }

}