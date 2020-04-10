package com.jiangxiacollege.canteenwebsite.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private LoginInterceptor loginInterceptor;//登录拦截器

    //图片存放根路径，从application.yml中读取upload
    @Value("${upload}")
    private String UPLOAD_PATH;
    
    /**
     * 文件上传
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	//外部静态资源映射路径，用来上传文件
    	String filePath = "file:" + UPLOAD_PATH;
        registry.addResourceHandler("/upload/**").addResourceLocations(filePath);
    }
    
  //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截的管理器
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);//拦截的对象会进入这个类中进行判断
        registration.addPathPatterns("/**");//所有路径都被拦截
        registration.excludePathPatterns("/","/login","/UserController/login","/kaptcha","/static/**","/UserController/logout","/images/**","/upload/**","/register/**","/doRegister/**");//添加不拦截路径
    }

}
