package com.marco.ribbon.consumer;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by landun on 2018/8/8.
 */
@EnableCircuitBreaker       //开启断路器功能
@EnableDiscoveryClient          //注册为Eureka客户端
@SpringBootApplication
@EnableAutoConfiguration
@EnableHystrix
@EnableHystrixDashboard     //开启Hystrix 面板
@ServletComponentScan       //扫描Servlet组件
public class MainApplication {

    @Bean
    @LoadBalanced   //开启客户端负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
