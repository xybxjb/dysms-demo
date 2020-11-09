package cn.slipbend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:const.properties")
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 允许跨域的IP
     */
    @Value("${allowed-origin}")
    private String allowedOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
                // 设置所有的请求可以进行跨域
        registry.addMapping("/**")
                // 允许跨域的IP
                .allowedOrigins(allowedOrigin)
                // 请求的方法，可以不设置 有默认的
                .allowedMethods("*")
                // 请求头，可以不设置 有默认的
                .allowedHeaders("*");
    }
}
