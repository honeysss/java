package com.wcl.config;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocalResolver implements LocaleResolver {
//    解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String l = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault();//默认的 如果自己没有就使用默认的
        if(!StringUtils.isEmpty(l)) {
//            如果不为空获取到自己设置的语言 en_US
            String[] split = l.split("_");
//            生成一个新的
            locale = new Locale(split[0], split[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
