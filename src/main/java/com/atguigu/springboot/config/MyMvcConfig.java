package com.atguigu.springboot.config;

import com.atguigu.springboot.component.LoginHanderInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC

/**
 * spring 5 弃用 WebMvcConfigurerAdapter
 * extends WebMvcConfigurerAdapter 改为 implements WebMvcConfigurer
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //"/login.html","/","/user/login","/static/**"
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor( new LoginHanderInterceptor())
//                .addPathPatterns( "/**" )
//                .excludePathPatterns( "/admin/login",
//                        "/index.jsp","/css/**","/image/**",
//                        "/asserts/**","/static/**","/");
//    }
//
//
//    //解决静态资源文件夹被拦截
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }

//第二种实现方法：添加WebMvcConfigurer组件
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
            WebMvcConfigurer adapter = new WebMvcConfigurer() {
                @Override
                public void addInterceptors(InterceptorRegistry registry) {
                    registry.addInterceptor( new LoginHanderInterceptor())
                            .addPathPatterns( "/**" )
                            .excludePathPatterns( "/admin/login",
                                    "/index.jsp","/css/**","/image/**",
                                    "/asserts/**","/static/**","/");
                }


                //解决静态资源文件夹被拦截
                @Override
                public void addResourceHandlers(ResourceHandlerRegistry registry) {
                    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
                    WebMvcConfigurer.super.addResourceHandlers(registry);
                }
               @Override
               public void addViewControllers(ViewControllerRegistry registry) {
                     //将login.html映射到路径urlpath为："/"上
                     registry.addViewController("/").setViewName("login");
                     registry.addViewController("/login.html").setViewName("login");
                     registry.addViewController("/main.html").setViewName("dashboard");
                 }
               };
     return adapter;
}




}
