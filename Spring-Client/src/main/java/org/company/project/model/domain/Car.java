package org.company.project.model.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Car implements Serializable {
    private Long carId;
    private String model;
    private Timestamp deletedDate;
    private Long recordVersion;
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
