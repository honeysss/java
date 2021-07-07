package com.wcl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;
//在这个类里重写一些方法可以接管springboot的管理
@Configuration //让当前类成为一个配置类
//@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {
//    视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("login.html").setViewName("login");
        registry.addViewController("regist.html").setViewName("regist");
        registry.addViewController("index.html").setViewName("index");
        registry.addViewController("article-publish.html").setViewName("article-publish");
        registry.addViewController("article-modify.html").setViewName("article-modify");
        registry.addViewController("article-detail.html").setViewName("article-detail");
    }
//    自定义国际化组件
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }
}
