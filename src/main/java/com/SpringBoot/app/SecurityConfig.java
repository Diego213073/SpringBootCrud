package com.SpringBoot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
     http.authorizeRequests().antMatchers("/listar").permitAll().antMatchers("/form/**").hasAnyRole("Administrador")
     .antMatchers("/eliminar/**").hasAnyRole("Administrador")
     .anyRequest().authenticated()
     .and()
     .formLogin().loginPage("/login")
     .permitAll()
     .and()
     .logout().permitAll();
	}



	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		PasswordEncoder passwordEconder = passwordEncoder();
		UserBuilder users = User.builder().passwordEncoder(passwordEconder :: encode);
		
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("123456").roles("Administrador","Usuario"))
		.withUser(users.username("Alejandro").password("12345").roles("Usuario"));
		
	}


}
