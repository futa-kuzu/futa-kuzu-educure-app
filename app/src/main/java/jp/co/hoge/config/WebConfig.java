package jp.co.hoge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.hoge.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LoginCheckInterceptor())
	        .addPathPatterns("/**")
	        .excludePathPatterns(
	            "/login", 
	            "/logout", 
	            "/signup/**", 
	            "/passChange/**",
	            "/quit/*",
	            "/css/**",
	            "/js/**",
	            "/images/**",
	            "/webjars/**"
	        );
	}

}
