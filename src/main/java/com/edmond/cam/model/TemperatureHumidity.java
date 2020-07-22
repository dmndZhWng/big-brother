package com.edmond.cam.model;

public class TemperatureHumidity {

    private float temperature;
    private float humidity;

    public TemperatureHumidity(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }
}
