package com.edmond.cam.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/check")
public class CheckController {

    @GetMapping(value = "/status", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> checkStatus() {
        Date date = new Date();
        String response = "I am up Bro ;) @ " + date;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
