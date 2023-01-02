package com.sportyshoes.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	//Properties
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;	
	
	@Autowired
	private AccessDeniedHandler customAccessDeniedHandler;
	
	//Methods
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* Implementing JDBC authentication. 
		 * By default Spring Security uses the supplied data source (autowired above) to connect to the database 
		 * and loads the user details and authorities from USERS and AUTHORITIES tables, respectively. */
		System.out.println("dataSource = " + dataSource);
		auth.userDetailsService(userDetailsService); //called to lookup a user from the database
		System.out.println(auth.userDetailsService(userDetailsService));		
	}		
	
	/*Customize the Spring Security HTTPSecurity to instruct Spring to redirect to the login endpoint for user login. 
	 * The WebSecurityConfigurerAdapter provides default security configuration, so we have to override it for custom configuration */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		 
		//Note that the order of the antMatchers() elements is significant; the more specific rules need to come first, followed by the more general ones.
		// Endpoint: /login excludes the login page from authentication
		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/home").permitAll()
				.antMatchers("http://localhost:8080/swagger-ui.html/**").permitAll()
				.antMatchers("/api/admin/**").hasAuthority("ADMIN")
		        .antMatchers("/admin").hasAuthority("ADMIN")
				.antMatchers("/user").hasAuthority("USER")
				.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/login")				
				.and()
					.csrf().disable()				
					.logout()					
					.logoutSuccessHandler(
							new LogoutSuccessHandler() {					     
					    @Override
					    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					                Authentication authentication)
					            throws IOException, ServletException {
					         
					        System.out.println("This user logged out: " + authentication.getName());
					         
					        response.sendRedirect("/login");
					    }
					})
					.invalidateHttpSession(true)
				.and()
					.exceptionHandling()
					.accessDeniedHandler(customAccessDeniedHandler);		
	}	
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		//Allows static	content, such as CSS and images, to be excluded from authentication
		web.ignoring().antMatchers("/webjars/**", "/images/**", "/css/**");
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	/* ------ Password Encoder Bean - Spring Security excepts you to have a password encoder ----- */
	
	/* Declare a PasswordEncoder bean, which we’ll use both when creating
	new users and when authenticating users at login
	Spring Security offers several password encoders.
	It's important to understand that the password in the database is never decoded. 
	Instead, the password that the user enters at login is encoded using the same algorithm, 
	and it’s then compared with the encoded password in the database. 
	That comparison is performed in the PasswordEncoder’s matches() method.
	Password Encoders are beans that transform plain text password into hashes. 
	As the hashes cannot be reversed into plaintext, it is a secure way to store passwords.
	For the password encoding/hashing, Spring Security expects a password encoder implementation. 
	Also, it provides dogmatic implementations based on industry standards. 
	These encoders will be used in the password storing phases and validation phase of authentication.
	*/
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
		System.out.println("Inside bCryptPasswordEncoder()..");
        return new BCryptPasswordEncoder();
    }	
	
}//end class
