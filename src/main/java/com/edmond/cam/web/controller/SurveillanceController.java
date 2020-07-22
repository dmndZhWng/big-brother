package com.edmond.cam.web.controller;

import com.edmond.cam.model.PictureHolder;
import com.edmond.cam.service.SurveillanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping(value = "/surveillance")
public class SurveillanceController {

    @Autowired
    private SurveillanceService surveillanceService;

    @PostMapping(value = "/take-picture", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PictureHolder> takePicture(@RequestParam String username) {
        try {
            PictureHolder pictureHolder = new PictureHolder(surveillanceService.takePicture(username), String.valueOf(new Date()));
            return new ResponseEntity<>(pictureHolder, HttpStatus.OK);
        } catch (IOException | InterruptedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/temperature", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> temperature() {
        return null;
    }

    @PostMapping(value = "/humidity", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> humidity() {
        return null;
    }

    @PostMapping(value = "/motion", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> motion() {
        return new ResponseEntity<>(String.valueOf(surveillanceService.senseMotion()), HttpStatus.OK);
    }

    @GetMapping(value = "/fire", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> fire() {
        return new ResponseEntity<>(String.valueOf(surveillanceService.senseFire()), HttpStatus.OK);
    }
}
