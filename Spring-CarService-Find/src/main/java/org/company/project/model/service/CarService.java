package org.company.project.model.service;

import jakarta.transaction.RollbackException;
import org.company.project.model.domain.Car;
import org.company.project.model.repository.Crud;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope("singleton")
public class CarService {
    private final Crud carRepository;

    public CarService(Crud carRepository) {
        this.carRepository = carRepository;
    }

    public Car findCarById (Car car){
        return carRepository.findCarById(car);
    }
    public List<Car> findCarByPersonId (Car car){
        return carRepository.findCarByPersonId(car);
    }
    @Transactional(rollbackFor = RollbackException.class)
    public void saveReceiveCar (Car car){
        carRepository.insertReceiveCar(car);
    }
}
