package com.vector.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/","/**","/register","/register/**").permitAll()
        .and().formLogin().loginPage("/login")
        .loginProcessingUrl("/authenticate").usernameParameter("username").passwordParameter("password")
        .defaultSuccessUrl("/home")
        .failureUrl("/authenticate/fail")
        .and().authorizeRequests().antMatchers("/home","/home/**","/user","/user/**").hasRole("USER")
        ;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder();
    }
}
