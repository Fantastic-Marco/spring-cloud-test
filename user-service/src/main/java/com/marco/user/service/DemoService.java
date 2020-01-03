/**
 * Copyright (C), 2016-2019, 图匠数据
 * FileName: DemoService
 * Author:   Marco
 * Date:     2019/8/27 15:54
 * Description:
 * History:
 */
package com.marco.user.service;

import com.marco.user.test.entity.Test1;
import com.marco.user.test.entity.Test2;
import com.marco.user.test.entity.Test3;
import com.marco.user.test.service.ITest1Service;
import com.marco.user.test.service.ITest2Service;
import com.marco.user.test.service.ITest3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Marco
 * @create 2019/8/27
 * @since 1.0.0
 */
@Service
public class DemoService {
    @Autowired
    private ITest1Service test1Service;
    @Autowired
    private ITest2Service test2Service;
    @Autowired
    private ITest3Service test3Service;

    public String test(){
        System.out.println(1/0);
        return "hello";
    }

    @Transactional(rollbackFor = Exception.class)
    public void multInsert(){
        test1Service.save(new Test1("一八嘎"));
        test2Service.save(new Test2("二八嘎"));
        System.out.println(1/0);
        test3Service.save(new Test3(30));

    }

}