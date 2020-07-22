package com.edmond.cam.service.raspberry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class CameraService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CameraService.class);
    private final String imageDir;

    public CameraService(@Value("${image.directory}") String imageDir) {
        this.imageDir = imageDir;
    }

    public String takePicture(String username) throws IOException, InterruptedException {
        File image = this.takeStill();
        LOGGER.info("User : " + username + " asks for picture");
        try (FileInputStream fileInputStream = new FileInputStream(image)) {
            byte[] bytes = new byte[(int) image.length()];
            fileInputStream.read(bytes);
            return Base64.getEncoder().encodeToString(bytes);
        }
    }

    private File takeStill() throws IOException, InterruptedException {
        String filePath = imageDir + File.separator + "now.jpg";
        List<String> command = new ArrayList<>();
        command.add("raspistill");
        command.add("-e");
        command.add("jpg");
        command.add("-o");
        command.add(imageDir + File.separator + "now.jpg");
        command.add("-vf");
        command.add("-hf");
        command.add("-w");
        command.add("" + 300);
        command.add("-h");
        command.add("" + 300 * 0.75);
        command.add("-q");
        command.add("" + 75);
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        process.waitFor();
        return new File(filePath);
    }
}
