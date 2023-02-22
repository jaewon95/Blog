package com.project.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.blog.interceptor.BoardInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new BoardInterceptor())
				.addPathPatterns("/user/create") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다
				.addPathPatterns("/user/update/*")
				.addPathPatterns("/user/delete/*");
				
				
//				.addPathPatterns("/user/delete/*");
				
				
		
//				.excludePathPatterns("/경로") // 해당 경로는 인터셉터가 가로채지 않는다
	}
	
}
