package be.webtechie.javaspringrestdb.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import be.webtechie.javaspringrestdb.entity.SensorEntity;
import be.webtechie.javaspringrestdb.repository.SensorRepository;

@RestController
public class SensorResource {

	@Autowired
    private SensorRepository sensorRepository;

    @GetMapping("/sensors")
    public List<SensorEntity> retrieveAllSensors() {
        return sensorRepository.findAll();
    }
    
}