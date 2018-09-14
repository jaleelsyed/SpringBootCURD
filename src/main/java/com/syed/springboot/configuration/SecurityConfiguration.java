package com.syed.springboot.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
// @EnableWebMvc
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// datasource
//	@Autowired
//	private DataSource dataSource;

	@Autowired
	private UsersDetails userDetailsService;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	@Override
//	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	    auth.userDetailsService(userDetailsService()).passwordEncoder(encoder());
//	  }
//	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		antMatchers("/**").permitAll() // login page
				.anyRequest().authenticated()
				.and().csrf().disable()
				.formLogin()
				.loginPage("/login.html")
				.loginProcessingUrl("/login").defaultSuccessUrl("/")
				.and()
				.httpBasic()
				.and().logout();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());

		return authProvider;
	}
	
	

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

	/*
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * web.ignoring().antMatchers("/resources/**", "/static/**"); }
	 */

}
