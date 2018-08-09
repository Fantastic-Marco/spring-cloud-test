package com.marco.user.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by landun on 2018/8/8.
 */
@RestController
@RequestMapping("/")
public class DemoController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    DiscoveryClient client;

    @RequestMapping("/hello")
    @ResponseBody
    public Object hello() {
        Map<String, Object> result = new HashMap<String, Object>();
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("hello,host " + instance.getHost() + ",service_id " + instance.getServiceId());
        result.put("host",instance.getHost());
        result.put("service_id",instance.getServiceId());
        result.put("port",instance.getPort());
        return result;
    }

    @RequestMapping(value = "/add/{number}",method = RequestMethod.GET)
    public int add(@PathVariable("number") int number){
        logger.info("add number is " + number +" -- " + System.currentTimeMillis());
        return ++number;
    }
}
