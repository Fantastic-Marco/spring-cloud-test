package com.marco.feign.consumer.conf;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by landun on 2018/8/9.
 */
@Configuration
public class FeignLogConf {

    @Bean
    Logger.Level feignLoggerLevel(){
        //不展示日志
//        return Logger.Level.NONE;
        //仅记录请求方法，URL，响应状态码以及响应时间
        return Logger.Level.BASIC;
        //除了记录BASIC信息外，还会加入请求和响应的头信息
//        return Logger.Level.HEADERS;
        //记录所有请求与响应的明细，包括请求头，请求行，请求体等
//        return Logger.Level.FULL;
    }

}
