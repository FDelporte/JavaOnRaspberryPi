package be.webtechie.javaspringrestdb.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Maps a database entity from the table SENSORS to a Java object.
 * The ID is marked as the unique identifier.
 */
@Entity
@Table(name = "SENSORS", uniqueConstraints={@UniqueConstraint(name="UN_SENSOR_ID", columnNames={"ID"})})
public class SensorEntity {

    /**
     * Auto-generated identifier to have a unique key for this sensor.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Name of the sensor, a required value.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Relationship between the sensor and a list of measurements.
     */
    @OneToMany(
            mappedBy = "sensorEntity",
            cascade = {CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    private Set<MeasurementEntity> measurements = new HashSet<>();

    /**
     * No-argument constructor is needed for JPA.
     */
    public SensorEntity() {
        // NOP
    }

    /**
     * Constructor with a name value.
     * @param name
     */
    public SensorEntity(String name) {
        this.name = name;
    }

    // Getters and setters needed by JPA and the code.    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MeasurementEntity> getDataEntries() {
        return measurements;
    }

    public void setDataEntries(Set<MeasurementEntity> dataEntries) {
        this.measurements = dataEntries;
    }
}
