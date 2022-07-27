package com.codedecode.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.codedecode.demo.security.filter.JwtTokenFilter;
import com.codedecode.demo.security.provider.JwtAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private JwtTokenFilter jwtTokenFilter;
	
	@Autowired
    private JwtAuthenticationProvider customAuthenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()	
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated()
        // setting stateless session, because we choose to implement Rest API
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .logout()
        	.logoutUrl("/logout")
        	.clearAuthentication(true)
        	.invalidateHttpSession(true)
        	.deleteCookies("JSESSIONID", "refresh_token")
        	.permitAll();
        
     // adding the custom filter before UsernamePasswordAuthenticationFilter in the filter chain
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) {
	        auth.authenticationProvider(customAuthenticationProvider);
	    }
}
