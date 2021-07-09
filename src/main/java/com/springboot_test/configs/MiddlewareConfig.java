package com.springboot_test.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.springboot_test.middlewares.JwtApp;
import com.springboot_test.middlewares.JwtAdmin;
import com.springboot_test.middlewares.WriteLog;

/**
 * 中间件配置类
 * @author Administrator
 *
 */
@Configuration
public class MiddlewareConfig implements WebMvcConfigurer {
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
	   //拦截所有路由
       registry.addInterceptor(writeLog())
               .addPathPatterns("/**");
       
       //拦截以/api/开头的路径，@PassToken注解不验证
       registry.addInterceptor(authenticationApp())
               .addPathPatterns("/api/**");
       
       //拦截以/admin/开头的路径，@PassToken注解不验证
       registry.addInterceptor(authenticationAdmin())
               .addPathPatterns("/admin/**");
   }
   
   @Bean
   public JwtApp authenticationApp() {
       return new JwtApp();
   }
   
   @Bean
   public JwtAdmin authenticationAdmin() {
       return new JwtAdmin();
   }
   
   @Bean
   public WriteLog writeLog() {
       return new WriteLog();
   }
}    
