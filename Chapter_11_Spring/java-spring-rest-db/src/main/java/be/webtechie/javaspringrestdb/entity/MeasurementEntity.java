package be.webtechie.javaspringrestdb.entity;

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
 * Maps a database entity from the table SENSOR_VALUES to a Java object
 */
@Entity
@Table(name = "MEASUREMENTS", uniqueConstraints={@UniqueConstraint(name="UN_MEASUREMENT_ID", columnNames={"ID"})})
public class MeasurementEntity {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "SENSOR_ID", nullable = false, foreignKey = @ForeignKey(name="FK_MEASUREMENT_SENSOR"))
    private SensorEntity sensorEntity;

    @Column(nullable = false)
    private long timestamp;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private int value;

    /**
     * No-argument constructor is needed for hibernate.
     */
    public MeasurementEntity() {
        // NOP
    }

    public MeasurementEntity(SensorEntity sensorEntity, long timestamp, String key, int value) {
        this.sensorEntity = sensorEntity;
        this.timestamp = timestamp;
        this.key = key;
        this.value = value;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    
}
