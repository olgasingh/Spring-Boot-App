package com.example.demo.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
    @EnableWebSecurity
    public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
       @Override
       protected void configure(HttpSecurity http) throws Exception {
          http
             .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                //.antMatchers("/roles","/roles/*", "/users","/users/*").hasRole("Admins")
                //.antMatchers("/categories","/categories/*", "/products","/products/*").hasRole("Users")
                .and()
             .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
       }
       @Autowired
       public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
          auth
             .inMemoryAuthentication()
             .withUser("admin").password("{noop}password").roles("Admins");
             auth
             .inMemoryAuthentication()  
             .withUser("user").password("{noop}password").roles("Users");
       }
    }