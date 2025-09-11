package be.webtechie.javaspringrestdb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Maps a database entity from the table MEASUREMENTS to a Java object.
 * The ID is marked as the unique identifier.
 */
@Entity
@Table(name = "MEASUREMENTS", uniqueConstraints={@UniqueConstraint(name="UN_MEASUREMENT_ID", columnNames={"ID"})})
public class MeasurementEntity {

    /**
     * Auto-generated identifier to have a unique key for this sensor.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Relationship between the measurement and its sensor.
     */
    @ManyToOne
    @JoinColumn(name = "SENSOR_ID", nullable = false, foreignKey = @ForeignKey(name="FK_MEASUREMENT_SENSOR"))
    private SensorEntity sensorEntity;

    /**
     * Timestamp of the measurement.
     */
    @Column(nullable = false)
    private long timestamp;

    /**
     * Key for the type of measurement, e.g. "temperature", "distance"...
     */
    @Column(name="measurement_key", nullable = false)
    private String key;

    /**
     * Value of the measurement
     */
    @Column(name="measurement_value", nullable = false)
    private double value;

    /**
     * No-argument constructor is needed for JPA.
     */
    public MeasurementEntity() {
        // NOP
    }

    /**
     * Constructor with a name value.
     * @param sensorEntity
     * @param timestamp
     * @param key
     * @param value
     */
    public MeasurementEntity(SensorEntity sensorEntity, long timestamp, String key, double value) {
        this.sensorEntity = sensorEntity;
        this.timestamp = timestamp;
        this.key = key;
        this.value = value;
    }
    
    // Getters and setters needed by JPA and the code.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public SensorEntity getSensor() {
        return sensorEntity;
    }

    public void setSensor(SensorEntity sensorEntity) {
        this.sensorEntity = sensorEntity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
