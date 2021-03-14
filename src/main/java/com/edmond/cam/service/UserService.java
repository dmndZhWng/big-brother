package com.edmond.cam.service;

import com.edmond.cam.model.Authority;
import com.edmond.cam.model.User;
import com.edmond.cam.util.ResourceHelper;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService implements UserDetailsService {

    private static final Map<String, UserDetails> CACHE = new ConcurrentHashMap<>();

    public UserService(Environment environment) throws IOException {

        List<Credentials> credentials = ResourceHelper.getCredentialsFromFile(environment.getProperty("path.credentials"));
        int id = 0;
        for (Credentials credential : credentials) {
            User user = new User(
                    id++,
                    credential.getUsername(),
                    credential.getPassword(),
                    Arrays.asList(Authority.ADMIN));
            CACHE.put(user.getUsername(), user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return CACHE.get(s);
    }

    //
    public static class Credentials {
        String username;
        String password;

        public Credentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
