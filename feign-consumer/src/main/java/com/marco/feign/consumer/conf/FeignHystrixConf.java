package com.marco.feign.consumer.conf;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by landun on 2018/8/9.
 */
@Configuration
public class FeignHystrixConf {

//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder(){
//        Feign.Builder builder = Feign.builder();
//        return builder;
//    }
}
