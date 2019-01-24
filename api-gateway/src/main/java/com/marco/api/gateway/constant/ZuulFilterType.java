/**
 * Copyright (C), 2016-2019, 图匠数据
 * FileName: FilterType
 * Author:   ImageDT-Javaer-1
 * Date:     2019/1/24 16:20
 * Description: Zuul 拦截类型
 * History:
 */
package com.marco.api.gateway.constant;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Zuul 拦截类型〉
 * zuul 中请求的生命周期如下
 * pre-->routing---->post
 *        |            |
 *        |--->error--|
 *
 * @author ImageDT-Javaer-1
 * @create 2019/1/24
 * @since 1.0.0
 */
public enum ZuulFilterType {
    PRE("pre"),
    ROUTING("routing"),
    ERROR("error"),
    POST("post");

    private String value;

    ZuulFilterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}