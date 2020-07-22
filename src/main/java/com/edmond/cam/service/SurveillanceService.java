package com.edmond.cam.service;

import com.edmond.cam.service.raspberry.CameraService;
import com.edmond.cam.service.raspberry.FireService;
import com.edmond.cam.service.raspberry.MotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SurveillanceService {

    @Autowired
    private CameraService cameraService;

    @Autowired
    private FireService fireService;

    @Autowired
    private MotionService motionService;

    public SurveillanceService() {
    }

    public String takePicture(String username) throws IOException, InterruptedException {
        return cameraService.takePicture(username);
    }

    public boolean senseFire() {
        return fireService.senseFire();
    }

    public boolean senseMotion() {
        return motionService.senseMotion();
    }
}
