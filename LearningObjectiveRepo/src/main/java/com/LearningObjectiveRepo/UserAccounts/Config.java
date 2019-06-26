package com.LearningObjectiveRepo.UserAccounts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
//import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
//import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
@EnableSpringHttpSession 
public class HttpSessionConfig {

	@Bean
	public LettuceConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory(); 
	}

	@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		return HeaderHttpSessionIdResolver.xAuthToken(); 
	}
}

@Configuration
@EnableRedisHttpSession

public class Config {
    @Bean
    public JedisConnectionFactory connectionFactory() {
            return new JedisConnectionFactory(); 
    }
}
*/