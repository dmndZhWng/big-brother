package com.edmond.cam.web;

import com.edmond.cam.service.SurveillanceService;
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
import java.io.OutputStream;

@RestController
@RequestMapping(value = "/surveillance")
public class SurveillanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpInterceptorImpl.class);

    private SurveillanceService surveillanceService;

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
            HttpHeaders httpHeaders = makeHeader();
            StreamingResponseBody streamingResponseBody = (outputStream) -> {
                transfer(inputStream, outputStream);
            };

            return ResponseEntity
                    .ok()
                    .headers(httpHeaders)
                    .body(streamingResponseBody);

        } catch (IOException e) {
            LOGGER.error("Shit happens on the camera ...");
        }

        return null;
    }

    private HttpHeaders makeHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control", "no-cache, no-store, must-revalidate");
        headers.add("Age", "0");
        headers.add("Expires", "0");
        headers.add("Cache-Control", "no-cache, private");
        headers.add("Pragma", "no-cache");
        headers.add("Content-Type", "multipart/x-mixed-replace; boundary=FRAME");

        return headers;
    }

    private void transfer(InputStream source, OutputStream target) throws IOException {
        byte[] buf = new byte[2048];
        int length;
        while ((length = source.read(buf)) > 0) {
            target.write(buf, 0, length);
            target.flush();
        }
    }
}
