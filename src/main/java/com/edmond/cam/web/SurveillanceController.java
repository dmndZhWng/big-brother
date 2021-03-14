package com.edmond.cam.web;

import com.edmond.cam.service.SurveillanceService;
import com.edmond.cam.util.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/surveillance")
public class SurveillanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpInterceptorImpl.class);

    private final SurveillanceService surveillanceService;

    public SurveillanceController(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    @GetMapping(value = "/fire", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> fire() {
        return new ResponseEntity<>(String.valueOf(surveillanceService.senseFire()), HttpStatus.OK);
    }

    @GetMapping(value = "/stream", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> stream() {

        try {
            InputStream inputStream = surveillanceService.readStream();
            HttpHeaders httpHeaders = HttpHelper.makeHeader();
            StreamingResponseBody streamingResponseBody = (outputStream) -> {
                HttpHelper.transfer(inputStream, outputStream);
            };

            return ResponseEntity
                    .ok()
                    .headers(httpHeaders)
                    .body(streamingResponseBody);

        } catch (IOException e) {
            LOGGER.error("Shit happens on the camera ...");
            return null;
        }
    }
}
