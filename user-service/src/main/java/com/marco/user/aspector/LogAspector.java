///**
// * Copyright (C), 2016-2019, 图匠数据
// * FileName: LogAspector
// * Author:   Marco
// * Date:     2019/8/12 10:11
// * Description: 日志切面
// * History:
// */
//package com.marco.user.aspector;
//
//import org.apache.log4j.Logger;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈日志切面〉
// *
// * @author Marco
// * @create 2019/8/12
// * @since 1.0.0
// */
//@Aspect
//@Configuration
//public class LogAspector {
//    private final Logger logger = Logger.getLogger(getClass());
//
//    @Pointcut("execution(* com.marco.user.controller.DemoController.*(..))")
//    public void logPointCut(){
//
//    }
//
//    @Before(value = "logPointCut()")
//    public void logBefore(){
//        logger.info("hello before");
//    }
//
//    @After(value = "logPointCut()")
//    public void logAfter(){
//        logger.info("hello after");
//    }
//
//}