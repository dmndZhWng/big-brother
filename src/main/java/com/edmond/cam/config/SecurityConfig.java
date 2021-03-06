package com.edmond.cam.config;

import com.edmond.cam.auth.CustomFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();

        http
                .headers()
                .frameOptions()
                .sameOrigin();

        http
                .addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);

        http
                .httpBasic();

        http
                .formLogin();

        http
                .authorizeRequests()
                .antMatchers("/check/status").permitAll()
                .anyRequest().authenticated()
        ;
    }
}
