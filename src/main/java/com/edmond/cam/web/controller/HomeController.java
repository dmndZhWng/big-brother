package com.edmond.cam.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Principal> getUser(HttpServletRequest httpServletRequest) {
        String authToken = httpServletRequest.getHeader("Authorization").substring("Basic".length()).trim();
        return new ResponseEntity<>(() -> new String(Base64.getDecoder().decode(authToken)).split(":")[0], HttpStatus.OK);
    }

    @GetMapping(value = "/adminRight", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> adminRight() {
        String response = "ADMIN";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/userRight", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> commonRight() {
        String response = "USER";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
