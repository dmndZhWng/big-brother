package com.edmond.cam.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.stereotype.Service;

@Service
public class FireService {

    private final GpioPinDigitalInput input;

    public FireService() {
        GpioController gpioController = GpioFactory.getInstance();
        this.input = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_12, PinPullResistance.PULL_DOWN);
        this.input.setShutdownOptions(true);
    }

    public boolean senseFire() {
        return this.input.getState().equals(PinState.HIGH);
    }
}
