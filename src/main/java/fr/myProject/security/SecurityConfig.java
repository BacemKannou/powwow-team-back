package fr.myProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder encoder = getBCPE();
//		auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("1234")).roles("ADMIN","USER")
//		.and()
//		.withUser("user").password(encoder.encode("1234")).roles("USER");
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcryptEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
//		http.authorizeRequests().anyRequest().permitAll();
//		http.csrf().disable();
		//http.authorizeRequests().anyRequest().permitAll();
//		http.formLogin();
//		http.addFilterBefore(new JWTAuthorizationFilter(),  UsernamePasswordAuthenticationFilter.class);
		
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/login/**", "/register/**" , "/products/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/Products/**").permitAll();
//		http.authorizeRequests().antMatchers(HttpMethod.POST, "/tasks").hasAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
