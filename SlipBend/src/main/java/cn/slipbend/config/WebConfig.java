package cn.slipbend.config;

import cn.slipbend.enums.BaseEnumConverterFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:const.properties")
public class WebConfig implements WebMvcConfigurer {
    @Value("${web.virtual-path}")
    private String virtualPath;

    @Value("${web.real-path}")//部署到服务器后改为web.real-path
    private String realPath;

    /*@Value("${web.real-path}")
    private String realPath;*/
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 注册ConverterFactory(类型转换器工厂)
        registry.addConverterFactory(new BaseEnumConverterFactory());
    }

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new SessionHandlerInterceptor());
        interceptorRegistration.excludePathPatterns("/error");
        interceptorRegistration.excludePathPatterns("/static/**");
        interceptorRegistration.excludePathPatterns("/login");
        interceptorRegistration.excludePathPatterns("/find");
        interceptorRegistration.excludePathPatterns("/save");
        interceptorRegistration.excludePathPatterns("/index");

        interceptorRegistration.addPathPatterns("/**");

    }*/


    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addResourceHandlers(registry);
    	registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler(virtualPath).addResourceLocations(realPath);
        System.out.println("WebConfig-values:"+virtualPath+"path:"+realPath);
	}


	/*private class SessionHandlerInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        	System.out.println(request.getServletPath());
            Object user = request.getSession().getAttribute("user");
            *//*if (user == null) {
                try {
                    response.sendRedirect("/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;

            }*//*
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
            //controller方法处理完毕后，调用此方法
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
            //页面渲染完毕后调用此方法
        }
    }*/
}
