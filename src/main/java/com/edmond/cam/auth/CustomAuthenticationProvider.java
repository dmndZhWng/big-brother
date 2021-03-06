package com.edmond.cam.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Bean
    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    private UserService userService;

    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userService.loadUserByUsername(authentication.getName());
        if (userDetails != null) {
            String rawPassword = authentication.getCredentials().toString();
            String encodedPassword = userDetails.getPassword();
            boolean isMatched = encoder().matches(rawPassword, encodedPassword);
            if (isMatched) {
                return new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities());
            } else {
                throw new BadCredentialsException("Incorrect user / password");
            }
        } else {
            throw new BadCredentialsException("Incorrect user / password");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
