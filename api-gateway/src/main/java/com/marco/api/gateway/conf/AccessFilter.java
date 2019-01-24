package com.marco.api.gateway.conf;

import com.marco.api.gateway.constant.ZuulFilterType;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by landun on 2018/8/10.
 */
@Component
public class AccessFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器请求类型，决定了该过滤器在请求的哪个生命周期执行
     * pre 请求路由之前被调用
     * routing 在路由请求时被调用
     * post 在routing和error之后被调用
     * error 在请求发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return ZuulFilterType.PRE.getValue();
    }

    /**
     * 过滤器执行顺序，根据该方法返回的值来决定先后
     * 数值越小优先等级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否应该被执行
     * 我们可以用过该方法来指定过滤器的有效范围
     * 根据业务需要来决定要不要使用该过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器具体执行逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
        Object accessToken = request.getParameter("access_token");
        if (accessToken == null){
            logger.warn("access token is null");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }
        logger.info("access token is permit");
        return null;
    }
}
