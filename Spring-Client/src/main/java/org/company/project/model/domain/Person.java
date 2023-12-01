package org.company.project.model.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Person implements Serializable {
    private Long personId;
    private String name;
    private String family;
    private Timestamp deletedDate;
    private Long recordVersion;
    private List<Long> carId;

    public Long getPersonId() {
        return personId;
    }

    public Person setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Person setFamily(String family) {
        this.family = family;
        return this;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public Person setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
        return this;
    }

    public Long getRecordVersion() {
        return recordVersion;
    }

    public Person setRecordVersion(Long recordVersion) {
        this.recordVersion = recordVersion;
        return this;
    }

    public List<Long> getCarId() {
        return carId;
    }

    public Person setCarId(List<Long> carId) {
        this.carId = carId;
        return this;
    }
}
