package org.company.project.model.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.company.project.model.domain.Person;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class PersonRepository {
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public Person save (Person person){
        return entityManager.merge(person);
    }

    public void saveWithoutMerge (Person person) {
        entityManager
                .createNativeQuery("insert into person (person_id,name,family,record_version,deleted_date) values (?,?,?,?,?)")
                .setParameter(1, person.getPersonId())
                .setParameter(2, person.getName())
                .setParameter(3,person.getFamily())
                .setParameter(4,person.getRecordVersion())
                .setParameter(5, person.getDeletedDate())
                .executeUpdate();
    }

}
