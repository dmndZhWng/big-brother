package com.edmond.cam.service;

import com.edmond.cam.model.Direction;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.stereotype.Service;

@Service
public class WheelService {

    private static final int DEFAULT_DURATION = 1;

    private final GpioPinDigitalOutput leftOutputForward;
    private final GpioPinDigitalOutput leftOutputBackward;

    private final GpioPinDigitalOutput rightOutputForward;
    private final GpioPinDigitalOutput rightOutputBackward;

    public WheelService() {
        GpioController gpioController = GpioFactory.getInstance();

        this.leftOutputBackward = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_25, PinState.LOW);
        this.leftOutputForward = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_24, PinState.LOW);

        this.rightOutputBackward = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_27, PinState.LOW);
        this.rightOutputForward = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_28, PinState.LOW);

        this.leftOutputBackward.setShutdownOptions(true, PinState.LOW);
        this.rightOutputBackward.setShutdownOptions(true, PinState.LOW);
    }

    public void move(Direction direction) {
        this.move(direction, DEFAULT_DURATION);
    }

    public void move(Direction direction, int duration) {
        switch (direction) {
            case LEFT:
                left(duration);
                break;
            case RIGHT:
                right(duration);
                break;
            case FORWARD:
                forward(duration);
                break;
            case BACKWARD:
                backward(duration);
                break;
            default:
                throw new IllegalArgumentException("Unknown direction");
        }
    }

    private void left(int second) {
        this.clearState();
        this.rightOutputForward.high();
        this.sleep(second);
        this.clearState();
    }

    private void right(int second) {
        this.clearState();
        this.leftOutputForward.high();
        this.sleep(second);
        this.clearState();
    }

    private void backward(int second) {
        this.clearState();
        this.leftOutputBackward.high();
        this.rightOutputBackward.high();
        this.sleep(second);
        this.clearState();
    }

    private void forward(int second) {
        this.clearState();
        this.leftOutputForward.high();
        this.rightOutputForward.high();
        this.sleep(second);
        this.clearState();
    }

    private void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            // ignore ...
        }
    }

    private void clearState() {
        this.leftOutputForward.low();
        this.rightOutputForward.low();
        this.leftOutputBackward.low();
        this.rightOutputBackward.low();
    }
}
