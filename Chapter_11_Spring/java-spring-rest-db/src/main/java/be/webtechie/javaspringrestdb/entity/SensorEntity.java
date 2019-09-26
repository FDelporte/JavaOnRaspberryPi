package be.webtechie.javaspringrestdb.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Maps a database entity from the table SENSORS to a Java object
 */
@Entity
@Table(name = "SENSORS", uniqueConstraints={@UniqueConstraint(name="UN_SENSOR_ID", columnNames={"ID"})})
public class SensorEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "sensorEntity",
            cascade = {CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    private Set<MeasurementEntity> measurements = new HashSet<>();

    /**
     * No-argument constructor is needed for hibernate.
     */
    public SensorEntity() {
        // NOP
    }

    public SensorEntity(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
