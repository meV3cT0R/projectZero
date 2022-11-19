package com.vector.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.vector.app.service.UserRepositoryUserDetailsServiceImpl;


@Configuration
public class NewSecurityConfig {

    @Bean
    public UserDetailsService loadUser() {
        return new UserRepositoryUserDetailsServiceImpl();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.formLogin(
            form->
            form
            .loginPage("/login")
            .loginProcessingUrl("/authenticate")
            .failureUrl("/authenticate/fail")
            .defaultSuccessUrl("/home")
            .usernameParameter("username")
            .passwordParameter("password")
        ).authorizeRequests().antMatchers("/","/register")
        .permitAll().and()
        .authorizeRequests()
        .antMatchers("/home","/home/**","/user","/user/**","/post","/post/**").authenticated();
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
