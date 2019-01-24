/**
 * Copyright (C), 2016-2019, 图匠数据
 * FileName: MainApplication
 * Author:   ImageDT-Javaer-1
 * Date:     2019/1/24 10:53
 * Description:
 * History:
 */
package com.marco;

import com.marco.sender.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ImageDT-Javaer-1
 * @create 2019/1/24
 * @since 1.0.0
 */

@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
public class MainApplication {

    @Autowired
    private KafkaSender kafkaSender;

    @PostConstruct
    public void init(){
        for (int i = 0; i < 10; i++) {
            //调用消息发送类中的消息发送方法
            kafkaSender.send("banana_finish_report","hello -> " + i);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}