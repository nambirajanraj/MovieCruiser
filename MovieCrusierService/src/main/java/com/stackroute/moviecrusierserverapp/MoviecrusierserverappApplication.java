package com.stackroute.moviecrusierserverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.moviecrusierserverapp.filter.JwtFilter;

@SpringBootApplication
public class MoviecrusierserverappApplication {

	@Bean
	public FilterRegistrationBean jwtFilter(){
		final FilterRegistrationBean registrationBean=new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		
		return registrationBean;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MoviecrusierserverappApplication.class, args);
	}

}
