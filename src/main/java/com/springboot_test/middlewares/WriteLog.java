package com.springboot_test.middlewares;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;

/**
 * 日志中间件
 * @author Administrator
 *
 */
public class WriteLog implements HandlerInterceptor {
		
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
    	HttpServletRequest request = (HttpServletRequest) httpServletRequest;
        HttpServletResponse response = (HttpServletResponse) httpServletResponse;

        Logger logger =LoggerFactory.getLogger(this.getClass());
        logger.info("【日志】requestUri:{},requestData:{}", request.getRequestURI(), JSON.toJSONString(request.getParameterMap()));
        
        //logger.debug("This is a debug message");//注意 spring 默认日志输出级别为 info 所以默认情况下 这句不会打印到控制台
        //logger.warn("This is a warn message");
        //logger.error("This is an error message");
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}