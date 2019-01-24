/**
 * Copyright (C), 2016-2019, 图匠数据
 * FileName: DefaultSender
 * Author:   ImageDT-Javaer-1
 * Date:     2019/1/24 10:54
 * Description:
 * History:
 */
package com.marco.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈用于发送消息〉
 *
 * @author ImageDT-Javaer-1
 * @create 2019/1/24
 * @since 1.0.0
 */

@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String message) {
        System.out.println("kafka send " + topic + "----> " + message);
        kafkaTemplate.send(topic, message);
    }
}