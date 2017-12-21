package com.cqjysoft.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置了可以被跨域访问的路径和可以被哪些主机跨域访问
        registry.addMapping("/**").allowedOrigins("*", "*");
    }
}
