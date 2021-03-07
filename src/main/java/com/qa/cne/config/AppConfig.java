package com.qa.cne.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import org.modelmapper.ModelMapper;

@Configuration
@Profile("test, dev")
public class AppConfig {

	@Bean
	@Scope("Prototype")
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
//	@Bean
//	public WenMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings
//		}
//	}
}
