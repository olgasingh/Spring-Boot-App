package com.example.demo.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
    @EnableWebSecurity
    public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
       @Override
       protected void configure(HttpSecurity http) throws Exception {
          http
          .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
      .and()
             .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/coaches","/coaches/*", "/games","/games/*").hasRole("Admins")
                .antMatchers("/players","/players/*", "/teams","/teams/*").hasRole("Admins")
                .antMatchers("/stats","/stats/*").hasRole("Admins")
                .antMatchers("/statictics","/statictics/*").hasRole("Users")
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

       @Bean
       CorsConfigurationSource corsConfigurationSource() {
           UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
           source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
           return source;
       }
    }