package com.edmond.cam.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping(value = "/error")
    public String error() {
        return "INVALID REQUEST >.<";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
