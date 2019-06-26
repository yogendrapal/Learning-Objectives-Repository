package com.LearningObjectiveRepo.UserAccounts;

import java.io.IOException;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/* @EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
        .passwordEncoder(getPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("**//*secured/**").authenticated()
                .anyRequest().permitAll() ;
    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }
}
*/
@Configuration
@EnableWebSecurity
//@EnableRedisHttpSession

// Modifying or overriding the default spring boot security.
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService appUserDetailsService;

	// This method is for overriding the default AuthenticationManagerBuilder.
	// We can specify how the user details are kept in the application. It may
	// be in a database, LDAP or in memory.
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserDetailsService).passwordEncoder(getPasswordEncoder());
	}

	// this configuration allow the client app to access the this api 
	// all the domain that consume this api must be included in the allowed o'rings 
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**").allowedOrigins("http://localhost:4200");
	          
	        }
	    };
	}
	// This method is for overriding some configuration of the WebSecurity
	// If you want to ignore some request or request patterns then you can
	// specify that inside this method
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	// This method is used for override HttpSecurity of the web Application.
	// We can specify our authorization criteria inside this method.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
		// starts authorizing configurations
		.authorizeRequests()
		// ignoring the guest's urls "
		.antMatchers("/api/users/register","/api/users/login", "/api/users/token").permitAll()
		.antMatchers(HttpMethod.GET,"/api/los").permitAll()
		// authenticate all remaining URLS
		.anyRequest().authenticated().and()
      /* "/logout" will log the user out by invalidating the HTTP Session,
       * cleaning up any {link rememberMe()} authentication that was configured, */
		.logout()
        .permitAll().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.ACCEPTED))
		//.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).clearAuthentication(true).invalidateHttpSession(true)
        .and()
		// enabling the basic authentication
		.httpBasic().and()
		// configuring the session on the server
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
		// disabling the CSRF - Cross Site Request Forgery
		.csrf().disable();
	}
	
	private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }
	
	
	/*
	 @Bean
	    public JedisConnectionFactory connectionFactory() throws IOException {
	        return new JedisConnectionFactory();
	    }

	
	 @Bean
	  HeaderHttpSessionStrategy sessionStrategy() {
	    return new HeaderHttpSessionStrategy();
	  }

	@Bean
	public LettuceConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory(); 
	}

	@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
	    return HeaderHttpSessionIdResolver.authenticationInfo(); 
	}
*/
}