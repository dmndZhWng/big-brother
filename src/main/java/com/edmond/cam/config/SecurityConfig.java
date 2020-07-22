package com.edmond.cam.config;

import com.edmond.cam.model.User;
import com.edmond.cam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    private static final String ENCRYPTION_ID = "{bcrypt}";

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        User edmond = userService.getUser("eywang");
        User yinjia = userService.getUser("yinjia");
        User tstUsr = userService.getUser("aaa");

        auth.inMemoryAuthentication()
                .withUser(edmond.getUsername()).password(ENCRYPTION_ID + edmond.getPassword()).roles("ADMIN", "USER")
                .and().withUser(yinjia.getUsername()).password(ENCRYPTION_ID + yinjia.getPassword()).roles("USER")
                .and().withUser(tstUsr.getUsername()).password(ENCRYPTION_ID + tstUsr.getPassword()).roles("USER")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();

        http
                .httpBasic()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/surveillance/status").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/home/adminRight").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/home/userRight").hasRole("USER")
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/home/user").hasRole("USER")
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
