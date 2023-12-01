package org.company.project.model.domain;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "PERSON")
@Entity(name = "person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(generator = "custom_generator")
    @GenericGenerator(name = "custom_generator", strategy = "org.company.project.model.repository.MyGenerator")
    private Long personId;
    @Column(length = 20)
    private String name;
    @Column(length = 20)
    private String family;
    @Version
    @Column(name = "RECORD_VERSION")
    private Long recordVersion;
    @Column(name = "DELETED_DATE")
    private Timestamp deletedDate;

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

    public Long getRecordVersion() {
        return recordVersion;
    }

    public Person setRecordVersion(Long recordVersion) {
        this.recordVersion = recordVersion;
        return this;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public Person setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
        return this;
    }
}
