package com.edmond.cam.web;

import com.edmond.cam.model.Direction;
import com.edmond.cam.service.WheelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/meca")
public class MechanicController {

    private WheelService wheelService;

    public MechanicController(WheelService wheelService) {
        this.wheelService = wheelService;
    }

    @PostMapping(value = "/move")
    public void forward(@RequestParam String direction) {
        wheelService.move(Direction.buildDirection(direction));
    }

    @PostMapping(value = "/move-with-duration")
    public void forward(@RequestParam String direction, @RequestParam int duration) {
        wheelService.move(Direction.buildDirection(direction), duration);
    }
}
