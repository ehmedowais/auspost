package com.auspost.codingtest.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	/*
        auth.inMemoryAuthentication()
                .withUser("user1").password("secret1").roles("USER")
                .and()
                .withUser("user2").password("secret2").roles("USER");
        */
        
		auth.jdbcAuthentication().dataSource(dataSource).
		authoritiesByUsernameQuery("select username,password,enabled from users where username =?")
		.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
		
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        //http.authorizeRequests().antMatchers("/auspost/location").fullyAuthenticated();
    	http.authorizeRequests().antMatchers("/auspost/location")
    	.access("hasRole('ROLE_USER')").anyRequest().permitAll();
        http.httpBasic();
        http.csrf().disable();
    }
}