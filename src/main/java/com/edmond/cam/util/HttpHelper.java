package com.edmond.cam.util;

import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpHelper {

    public static HttpHeaders makeHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control", "no-cache, no-store, must-revalidate");
        headers.add("Age", "0");
        headers.add("Expires", "0");
        headers.add("Cache-Control", "no-cache, private");
        headers.add("Pragma", "no-cache");
        headers.add("Content-Type", "multipart/x-mixed-replace; boundary=FRAME");

        return headers;
    }

    public static void transfer(InputStream source, OutputStream target) throws IOException {
        byte[] buf = new byte[2048];
        int length;
        while ((length = source.read(buf)) > 0) {
            target.write(buf, 0, length);
            target.flush();
        }
    }
}
