package com.sabtok.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/*
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home","/js/**", "/css/**","/lib/**").permitAll()		
				.antMatchers("/*").hasRole("ADMIN")			
				.and()
			.formLogin()
				.loginPage("/login").failureUrl("/login-error");	
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin").password("admin").roles("ROLE_ADMIN");
	}
}
*/