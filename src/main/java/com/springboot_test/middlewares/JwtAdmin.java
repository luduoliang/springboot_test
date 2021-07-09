package com.springboot_test.middlewares;

import com.springboot_test.configs.PassToken;
import com.springboot_test.utils.JwtUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 后端token验证中间件
 * @author Administrator
 *
 */
public class JwtAdmin implements HandlerInterceptor {
	
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
    	HttpServletRequest request = (HttpServletRequest) httpServletRequest;
        //HttpServletResponse response = (HttpServletResponse) httpServletResponse;
        
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        } 
        //默认全部检查
        else {
        	// 从请求头中取出 token  这里需要和前端约定好把jwt放到请求头一个叫token的地方
            String token = httpServletRequest.getHeader("Authorization");
            token = token.replace("Bearer ", "");
            //System.out.println("被jwt拦截需要验证");
            // 执行认证
            if (token == null) {
                //这里其实是登录失效,没token了   这个错误也是我自定义的，读者需要自己修改
                throw new Exception("用户状态异常1");
            }
            
            //获取token中的 userId
            String userId = JwtUtils.getSubjectValue(token);
            System.out.println(userId);

            if (userId == null) {
                //这个错误也是我自定义的
                throw new Exception("用户状态异常2");
            }

            // 验证 token,如果不是正常token，抛出异常
            JwtUtils.verifyToken(token);
                
        	
            //放入attribute以便后面调用
            request.setAttribute("userId", userId);
            return true;

        }
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