package com.example.demo.Configurations;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
    @EnableWebSecurity
    public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
       @Override
       protected void configure(HttpSecurity http) throws Exception {          
          http
          //.csrf().ignoringAntMatchers("/nocsrf","/ignore/startswith/**")//security 4
          //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
          .csrf().disable()
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

                //http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
                 // private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
                  //private RegexRequestMatcher apiMatcher = new RegexRequestMatcher("/v[0-9]*/.*", null);
              /*
                  @Override
                  public boolean matches(HttpServletRequest request) {
                      // No CSRF due to allowedMethod
                      if(allowedMethods.matcher(request.getMethod()).matches())
                          return false;
              
                      // No CSRF due to api call
                      if(apiMatcher.matches(request))
                          return false;
              
                      // CSRF for everything else that is not an API call or an allowedMethod
                      return true;
                  }
              });*/
              
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