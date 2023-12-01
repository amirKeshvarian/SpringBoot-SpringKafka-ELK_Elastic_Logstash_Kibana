package org.company.project.model.service;

import jakarta.transaction.RollbackException;
import lombok.extern.slf4j.Slf4j;
import org.company.project.model.domain.Car;
import org.company.project.model.repository.CarRepository;
import org.hibernate.mapping.Collection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Scope("singleton")
@Slf4j
public class CarService {
    private final CarRepository carRepository;
    private static final List<Long> carIdList = new ArrayList<>();//because this microservice do not insert the record that own send to kafka
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional(rollbackFor = RollbackException.class)
    public Car save (Car car) throws Exception{
        Car saveCar = carRepository.save(car);
        Collections.synchronizedList(carIdList).add(saveCar.getCarId());
        return saveCar;
    }

    @Transactional
    public void saveFromKafka (Car car) {
        if (!Collections.synchronizedList(carIdList).contains(car.getCarId())){
            carRepository.saveWithoutMerge(car);
            Collections.synchronizedList(carIdList).add(car.getCarId());
            log.info("save person from kafka : " + car.getCarId() + " " + car.getModel() + " " + car.getPersonId());
        }
    }
}
