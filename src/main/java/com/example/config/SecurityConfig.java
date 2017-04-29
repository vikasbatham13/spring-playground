package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeRequests().mvcMatchers("/flights/**", "/math/**","/lessons/**","/words/**").permitAll();
        httpSecurity.authorizeRequests().mvcMatchers("/admin/**").hasRole("ADMIN");
        httpSecurity.authorizeRequests().anyRequest().authenticated();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication().withUser("employee").password("my-employee-password").roles("EMPLOYEE");
        authenticationManagerBuilder.inMemoryAuthentication().withUser("boss").password("my-boss-password").roles("MANAGER","ADMIN");
        authenticationManagerBuilder.inMemoryAuthentication().withUser("Vikas").password("test").roles("MANAGER");
        authenticationManagerBuilder.inMemoryAuthentication().withUser("Batham").password("test").roles("ADMIN");
    }
}