package be.webtechie.javaspringrestdb.resource;

import be.webtechie.javaspringrestdb.entity.MeasurementEntity;
import be.webtechie.javaspringrestdb.entity.SensorEntity;
import be.webtechie.javaspringrestdb.repository.MeasurementRepository;
import be.webtechie.javaspringrestdb.repository.SensorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasurementResource {

    @Autowired
    private SensorRepository sensorRepository;

	@Autowired
    private MeasurementRepository measurementRepository;

    @GetMapping("/measurement")
    public List<MeasurementEntity> retrieveAllMeasurements() {
        return measurementRepository.findAll();
    }

    @PostMapping("/measurement")
    public ResponseEntity createMeasurement(
            @RequestParam long sensorId,
            @RequestParam String key,
            @RequestParam double value) {

        SensorEntity sensorEntity = sensorRepository.findById(sensorId);

        if (sensorEntity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No sensor defined with the ID: " + sensorId);
        }

        MeasurementEntity measurementEntity = new MeasurementEntity(
                sensorEntity, System.currentTimeMillis(), key, value);
        measurementRepository.save(measurementEntity);

        return ResponseEntity.ok().build();
    }
}