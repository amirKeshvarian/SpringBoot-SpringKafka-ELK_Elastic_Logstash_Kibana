package org.company.project.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.company.project.model.domain.Car;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository
@Scope("singleton")
public class CarRepository {
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public Car save (Car car) {
        return entityManager.merge(car);
    }

    public void saveWithoutMerge (Car car) {
        entityManager
                .createNativeQuery("insert into car (car_id,model,person_id,record_version,deleted_date) values (?,?,?,?,?)")
                .setParameter(1, car.getCarId())
                .setParameter(2, car.getModel())
                .setParameter(3, car.getPersonId())
                .setParameter(4, car.getRecordVersion())
                .setParameter(5,car.getDeletedDate())
                .executeUpdate();
    }


}
