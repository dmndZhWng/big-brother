package com.edmond.cam.model;

public class PictureHolder {
    private String base64Code;
    private String currentTimeStamp;

    public PictureHolder(String base64Code, String currentTimeStamp) {
        this.base64Code = base64Code;
        this.currentTimeStamp = currentTimeStamp;
    }

    public String getBase64Code() {
        return base64Code;
    }

    public String getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public void setBase64Code(String base64Code) {
        this.base64Code = base64Code;
    }

    public void setCurrentTimeStamp(String currentTimeStamp) {
        this.currentTimeStamp = currentTimeStamp;
    }
}
