package com.nuc.finish.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/12 19:13
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:G:/finish-work/finish/src/main/resources/static/upload/");
        registry.addResourceHandler("/video/**").addResourceLocations("file:G:/finish-work/finish/src/main/resources/static/upload/");
    }
}
