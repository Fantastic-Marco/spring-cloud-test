package com.marco.user.controller;

import com.alibaba.fastjson.JSON;
//import com.imagedt.walnut.goods.common.vo.ProductCategoryVo;
import com.imagedt.walnut.goods.service.IntelGoodsProductService;
import com.marco.user.service.DemoService;
import com.marco.user.service.GoodsService;
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

    @Autowired
    DemoService demoService;

    @Autowired
    IntelGoodsProductService goodsProductService;

    @RequestMapping("/hello")
    @ResponseBody
    public Object hello() {
        Map<String, Object> result = new HashMap<String, Object>();
//        ServiceInstance instance = client.getLocalServiceInstance();
//        logger.info("hello,host " + instance.getHost() + ",service_id " + instance.getServiceId());
//        result.put("host",instance.getHost());
//        result.put("service_id",instance.getServiceId());
//        result.put("port",instance.getPort());
        demoService.multInsert();
        result.put("message","操作成功");
        return result;
    }

    @RequestMapping(value = "/add/{number}",method = RequestMethod.GET)
    public int add(@PathVariable("number") int number){
        logger.info("add number is " + number +" -- " + System.currentTimeMillis());
        return ++number;
    }

    @RequestMapping(value = "goods",method = RequestMethod.GET)
    public Object goods(){
        String json = "{\n" +
                "  \"barCode\": \"\",\n" +
                "  \"modelingStatus\": null,\n" +
                "  \"objectId\": null,\n" +
                "  \"productId\": null,\n" +
                "  \"objectName\": \"\",\n" +
                "  \"packageKey\": \"\",\n" +
                "  \"packageName\": \"\",\n" +
                "  \"projectKeyWord\": \"\",\n" +
                "  \"type\": null,\n" +
                "  \"pageNo\": 1,\n" +
                "  \"pageSize\": 60,\n" +
                "  \"order\": \"ASC\",\n" +
                "  \"orderField\": 1,\n" +
                "  \"brandFieldVoArrayList\": [],\n" +
                "  \"categoryFieldVoArrayList\": [],\n" +
                "  \"projectIdList\": []\n" +
                "}";
//        ProductCategoryVo vo = JSON.parseObject(json,ProductCategoryVo.class);
//        return goodsProductService.getObjectListAction(vo);
        return null;
    }
}
