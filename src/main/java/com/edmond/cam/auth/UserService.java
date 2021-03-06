package com.edmond.cam.auth;

import com.edmond.cam.model.Authority;
import com.edmond.cam.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService implements UserDetailsService {

    private static final String CREDENTIAL_PATH = "/home/pi/Java/credentials.txt";

    private static Map<String, UserDetails> CACHE = new ConcurrentHashMap<>();

    public UserService() throws IOException {

        List<Credentials> credentials = getCredentialsFromFile();
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

    private static List<Credentials> getCredentialsFromFile() throws IOException, IllegalFormatException {
        List<Credentials> credentials = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CREDENTIAL_PATH))) {
            String line = bufferedReader.readLine();
            String[] users = line.split("=")[1].trim().split(",");

            line = bufferedReader.readLine();
            String[] passwords = line.split("=")[1].trim().split(",");

            if (users.length != passwords.length) {
                throw new MissingFormatArgumentException("Users len and password len do not match !!!");
            }

            for (int i = 0; i < users.length; i++) {
                credentials.add(new Credentials(users[i], passwords[i]));
            }

            return credentials;
        }
    }

    private static class Credentials {
        String username;
        String password;

        Credentials(String username, String password) {
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
