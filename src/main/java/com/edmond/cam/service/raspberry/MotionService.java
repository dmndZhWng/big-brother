package com.edmond.cam.service.raspberry;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MotionService {

    private GpioController gpioController;
    private GpioPinDigitalInput input;

    public MotionService() {
    }

    public boolean senseMotion() {
        return input.getState().equals(PinState.HIGH);
    }

    @PostConstruct
    public void init() {
        gpioController = GpioFactory.getInstance();
        input = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_15, PinPullResistance.PULL_DOWN);
        input.setShutdownOptions(true);
        input.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                System.out.println("Motion Pin : " + input.getState());
            }
        });
    }
}
