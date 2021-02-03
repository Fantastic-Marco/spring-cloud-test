package com.marco;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by landun on 2018/8/8.
 */

@EnableEurekaServer
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MainApplication.class).run(args);
    }
}
