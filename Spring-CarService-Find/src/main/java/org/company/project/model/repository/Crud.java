package org.company.project.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.company.project.model.domain.Car;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("singleton")
public class Crud {
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public Car findCarById (Car car) {
        return entityManager.find(Car.class, car.getCarId());
    }
    public List<Car> findCarByPersonId (Car car){
        return entityManager.createQuery("select o from car o where o.personId=:personId")
                .setParameter("personId", car.getPersonId())
                .getResultList();
    }

    public void insertReceiveCar (Car car) {
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
