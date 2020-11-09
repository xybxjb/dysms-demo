package cn.slipbend.config;

import cn.slipbend.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                // 排除登录相关请求
                .excludePathPatterns("/login/sendSMS","/login/loginByPassword","/login/loginBySmscode","/login/loginByWeChatOrQQ","/login/updatePassword")
                // 排除 swagger 接口文档相关请求
                .excludePathPatterns("/*.html","/swagger-resources/**","/webjars/**")
                // 排除 图片访问 相关请求
                .excludePathPatterns("/images/**","/favicon.ico")
                // 排除 error 请求
                .excludePathPatterns("/error");
    }
}
