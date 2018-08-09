package com.marco.ribbon.consumer.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by landun on 2018/8/9.
 */
public class ConsumerCommand extends HystrixCommand<Integer> {
    private RestTemplate template;
    private int number;

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandKey");

    public ConsumerCommand(RestTemplate template,int number) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet"))
                .andCommandKey(GETTER_KEY));
        this.template = template;
        this.number = number;
    }

    @Override
    protected Integer run() throws Exception {
        return template.getForEntity("http://USER-SERVICE/add/{1}", Integer.class, number).getBody();
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(number);
    }

    /**
     * 清除缓存
     * @param number
     */
    public static void flushKey(int number){
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance())
                .clear(String.valueOf(number));
    }
}
