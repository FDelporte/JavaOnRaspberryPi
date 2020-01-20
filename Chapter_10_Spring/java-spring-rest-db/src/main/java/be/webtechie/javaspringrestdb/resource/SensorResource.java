package be.webtechie.javaspringrestdb.resource;

import be.webtechie.javaspringrestdb.entity.SensorEntity;
import be.webtechie.javaspringrestdb.repository.SensorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorResource {

	@Autowired
    private SensorRepository sensorRepository;

    @GetMapping("/sensor")
    public List<SensorEntity> retrieveAllSensors() {
        return sensorRepository.findAll();
    }

    @GetMapping("/sensor/{id}")
    public SensorEntity retrieveSensor(@PathVariable long id) {
        return sensorRepository.findById(id);
    }

    @PostMapping("/sensor")
    public ResponseEntity createSensor(@RequestParam String name) {
        List<SensorEntity> sensorEntities = sensorRepository.findAllByName(name);

        if (sensorEntities.size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("There is already a sensor with the name: " + name);
        }

        SensorEntity sensorEntity = new SensorEntity(name);
        sensorRepository.save(sensorEntity);
        return ResponseEntity.ok(sensorEntity);
    }
}