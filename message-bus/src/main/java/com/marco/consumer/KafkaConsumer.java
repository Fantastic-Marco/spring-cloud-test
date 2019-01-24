/**
 * Copyright (C), 2016-2019, 图匠数据
 * FileName: KafkaConsumer
 * Author:   ImageDT-Javaer-1
 * Date:     2019/1/24 11:13
 * Description: kafka消费类
 * History:
 */
package com.marco.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 〈一句话功能简述〉<br>
 * 〈kafka消费类〉
 *
 * @author ImageDT-Javaer-1
 * @create 2019/1/24
 * @since 1.0.0
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test", "banana_finish_report"})
    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        System.out.println(">>>>>>>>>> record =" + kafkaMessage);
        if (kafkaMessage.isPresent()) {
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            System.out.println(">>>>>>>>接收消息message =" + message);
        }

    }
}