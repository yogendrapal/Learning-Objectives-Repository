package com.LearningObjectiveRepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/*
 * Main function, starting point of the application
 */
@SpringBootApplication
public class LearningObjectiveRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningObjectiveRepoApplication.class, args);
	}
/*	 @Bean
	  HeaderHttpSessionStrategy sessionStrategy() {
	    return new HeaderHttpSessionStrategy();
	  }

*/
}

