/**
 * Copyright (C), 2016-2019, 图匠数据
 * FileName: KafkaConsumer
 * Author:   ImageDT-Javaer-1
 * Date:     2019/1/23 11:24
 * Description:
 * History:
 */
package com.marco;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ImageDT-Javaer-1
 * @create 2019/1/23
 * @since 1.0.0
 */
public class KafkaConsumerMainApplication {
    private KafkaConsumer<String, String> consumer;
    private ExecutorService executors;

    public KafkaConsumerMainApplication(List<String> topics) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.154.129:9092,192.168.154.129:9093,192.168.154.129:9094,192.168.154.129:9095");
        props.put("group.id", "java-application-1");
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 1000);
        props.put("session.timeout.ms", 30000);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(topics);

        System.out.println(consumer.subscription());

//        while (true) {
//            try {
//                System.out.println("开始获取消息");
//                ConsumerRecords<String, String> records = consumer.poll(300);
//                if (records.isEmpty()) {
//                    continue;
//                }
//                System.out.println(records.count());
//                for (ConsumerRecord record : records) {
//                    System.out.println("监听到kafka消息。。。。。。");
//                    executors.submit(new ConsumerWorker(record));
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }

    public void execute(int workerNum) {
//        executors = new ThreadPoolExecutor(workerNum, workerNum, 1000L, TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue(1000), new ThreadPoolExecutor.CallerRunsPolicy());
        executors = Executors.newFixedThreadPool(workerNum);
        Thread t = new Thread(new Runnable() {//启动一个子线程来监听kafka消息
            @Override
            public void run() {
                while (true) {
                    try {
//                        System.out.println("开始获取消息");
                        ConsumerRecords<String, String> records = consumer.poll(300);
                        if (records.isEmpty()) {
                            continue;
                        }
                        for (ConsumerRecord record : records) {
                            System.out.println("监听到kafka消息。。。。。。");
                            executors.submit(new ConsumerWorker(record));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

    }

    public void shutdown() {
        if (consumer != null) {
            consumer.close();
        }
        if (executors != null) {
            executors.shutdown();
        }
        try {
            if (!executors.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Timeout.... Ignore for this case");
            }
        } catch (InterruptedException ignored) {
            System.out.println("Other thread interrupted this shutdown, ignore for this case.");
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        List<String> topics = new LinkedList<String>();
        topics.add("banana_finish_report");
        KafkaConsumerMainApplication application = new KafkaConsumerMainApplication(topics);
        application.execute(2);
    }

}