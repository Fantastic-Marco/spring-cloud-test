/**
 * Copyright (C), 2016-2019, 图匠数据
 * FileName: MainAppliaction
 * Author:   ImageDT-Javaer-1
 * Date:     2019/1/22 19:02
 * Description: 启动类
 * History:
 */
package com.marco;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈启动类〉
 *
 * @author ImageDT-Javaer-1
 * @create 2019/1/22
 * @since 1.0.0
 */
public class MainAppliaction {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger("banana_finish_report");
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            logger.info("------------------- hello world ->" + i);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}