package org.company.project.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "CAR")
@Entity(name = "car")
public class Car implements Serializable {
    @Id
    private Long carId;
    @Column(length = 20)
    private String model;
    @Column(name = "DELETED_DATE")
    private Timestamp deletedDate;
    @Column(name = "RECORD_VERSION")
    private Long recordVersion;
    @Column(name = "PERSON_ID")
    private Long personId;

    public Long getCarId() {
        return carId;
    }

    public Car setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public Car setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
        return this;
    }

    public Long getRecordVersion() {
        return recordVersion;
    }

    public Car setRecordVersion(Long recordVersion) {
        this.recordVersion = recordVersion;
        return this;
    }

    public Long getPersonId() {
        return personId;
    }

    public Car setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }
}
