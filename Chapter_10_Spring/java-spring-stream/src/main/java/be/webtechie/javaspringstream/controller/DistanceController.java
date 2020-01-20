package be.webtechie.javaspringstream.controller;

import be.webtechie.javaspringstream.dto.DistanceMeasurement;
import be.webtechie.javaspringstream.service.DistanceService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class DistanceController {

    private final DistanceService distanceService;

    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping(
            value = "/distance",
            produces = MediaType.APPLICATION_STREAM_JSON_VALUE
    )
    public Flux<DistanceMeasurement> distance() {
        return this.distanceService.getDistances();
    }
}
