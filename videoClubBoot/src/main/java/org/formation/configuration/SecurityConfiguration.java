package org.formation.configuration;

import javax.sql.DataSource;

import org.formation.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	private MyUserDetailsService myUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/rest/**").authenticated().and().httpBasic().and().csrf().disable();
		
		// definir les acces aux pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/**/list").authenticated();
		http.authorizeRequests().antMatchers("/**/edit").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated().and() .formLogin().loginPage("/login").permitAll().and().logout().logoutSuccessUrl("/").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// authentification en memoire
		// auth.inMemoryAuthentication().withUser("toto").password("{noop}tutu").roles("ADMIN");
		// deuxieme solution

		// authentification avec une base de donnees
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password,enable from users where username=?")
				.authoritiesByUsernameQuery("select username,role from user_role where username =?");

		// authen avec userdetailservice

		auth.userDetailsService(myUserDetailsService).passwordEncoder(getPasswordEncoder());

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
}
