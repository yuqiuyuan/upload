package com.czb.upload;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName MyWebMvcConfig
 * @Description TODO
 * @Author drebander
 * @Date 2019-10-13 3:46 PM
 * @Version 1.0
 **/
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 当访问/file下的资源时，会到/root/myFile/下去找
        // 例如访问：http://localhost:8080/czb/1.png时会去找/root/myFile/1.png
        registry.addResourceHandler("/czb/**").addResourceLocations("file:/czb/upload/");
        super.addResourceHandlers(registry);
    }
}
