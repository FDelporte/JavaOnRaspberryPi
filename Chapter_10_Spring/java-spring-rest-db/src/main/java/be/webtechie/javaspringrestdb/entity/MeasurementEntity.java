package be.webtechie.javaspringrestdb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
    @Column(nullable = false)
    private String key;

    /**
     * Value of the measurement
     */
    @Column(nullable = false)
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
