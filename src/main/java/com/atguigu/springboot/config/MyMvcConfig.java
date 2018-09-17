package com.atguigu.springboot.config;

import com.atguigu.springboot.component.LoginHanderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors( registry );
        registry.addInterceptor( new LoginHanderInterceptor())
                .addPathPatterns( "/**" )
                .excludePathPatterns( "/admin/login","/index.jsp");
    }
}
