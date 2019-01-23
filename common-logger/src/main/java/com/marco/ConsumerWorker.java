/**
 * Copyright (C), 2016-2019, 图匠数据
 * FileName: ConsumerWorker
 * Author:   ImageDT-Javaer-1
 * Date:     2019/1/23 11:47
 * Description:
 * History:
 */
package com.marco;

import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.Callable;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ImageDT-Javaer-1
 * @create 2019/1/23
 * @since 1.0.0
 */
public class ConsumerWorker implements Runnable {

    private ConsumerRecord<String, String> consumerRecord;

    public ConsumerWorker(ConsumerRecord record) {
        this.consumerRecord = record;
    }

    @Override
    public void run() {
        System.out.println(consumerRecord.headers() + "  " +  consumerRecord.partition() + " value " + consumerRecord.value());
//        ConsumerMessageBO  consumerMessageBO= JSONObject.parseObject(consumerRecord.value(),ConsumerMessageBO.class);
//        consumerMessageBO.setOffset(consumerRecord.offset());
//        consumerMessageBO.setPartition(consumerRecord.partition());
//        for(MessageContainer messageContainer: PropertyFactory.consumerProperty.getMessageContainers()){
//            if(consumerRecord.topic().equals(messageContainer.getTopic())){
//                messageContainer.getMessageHandle().onMessage(consumerMessageBO);
//            }
//        }

    }
}