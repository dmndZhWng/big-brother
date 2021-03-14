package com.edmond.cam.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Service
public class SurveillanceService {

    private final String camAddress;

    private final FireService fireService;

    public SurveillanceService(FireService fireService, Environment environment) {
        this.fireService = fireService;
        this.camAddress = environment.getProperty("url.stream.cam");
    }

    public boolean senseFire() {
        return fireService.senseFire();
    }

    public InputStream readStream() throws IOException {
        URL url = new URL(camAddress);
        URLConnection urlConnection = url.openConnection();
        return urlConnection.getInputStream();
    }
}
