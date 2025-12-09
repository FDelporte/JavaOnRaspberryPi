package be.webtechie.javaspringstream.controller;

import be.webtechie.javaspringstream.dto.ButtonState;
import be.webtechie.javaspringstream.service.Pi4JService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class InputController {

    private final Pi4JService distanceService;

    public InputController(Pi4JService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping(
            value = "/button",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<ButtonState> button() {
        return this.distanceService.getButtonStates();
    }
}
