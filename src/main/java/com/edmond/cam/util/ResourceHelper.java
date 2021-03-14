package com.edmond.cam.util;

import com.edmond.cam.service.UserService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.MissingFormatArgumentException;

public class ResourceHelper {

    public static List<UserService.Credentials> getCredentialsFromFile(String fullPath) throws IOException, IllegalFormatException {
        List<UserService.Credentials> credentials = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fullPath))) {
            String line = bufferedReader.readLine();
            String[] users = line.split("=")[1].trim().split(",");

            line = bufferedReader.readLine();
            String[] passwords = line.split("=")[1].trim().split(",");

            if (users.length != passwords.length) {
                throw new MissingFormatArgumentException("Users len and password len do not match !!!");
            }

            for (int i = 0; i < users.length; i++) {
                credentials.add(new UserService.Credentials(users[i], passwords[i]));
            }

            return credentials;
        }
    }
}
