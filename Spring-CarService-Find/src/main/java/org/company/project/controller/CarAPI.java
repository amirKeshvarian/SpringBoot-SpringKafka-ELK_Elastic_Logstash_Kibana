package org.company.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.company.project.model.domain.Car;
import org.company.project.model.service.CarService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope("singleton")
@RequestMapping("/car")
@Slf4j
public class CarAPI {
    private final CarService carService;

    public CarAPI(CarService carService) {
        this.carService = carService;
    }
//    private final KafkaTemplate<String, Object> kafkaTemplate;

//    public CarAPI(CarService carService, KafkaTemplate<String, Object> kafkaTemplate) {
//        this.carService = carService;
//        this.kafkaTemplate = kafkaTemplate;
//    }
    @GetMapping("/findCarById.do")
    public ResponseEntity<Car> findCarById (@RequestParam("carId")String carId){
        Car car = carService.findCarById(new Car().setCarId(Long.parseLong(carId)));
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }
    @GetMapping("/findCarsByPersonId.do")
    public ResponseEntity<List<Car>> findCarsByPersonId (@RequestParam("personId")String personId){
        List<Car> cars = carService.findCarByPersonId(new Car().setPersonId(Long.parseLong(personId)));
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }
    @KafkaListener(topics = "car-event")
    public void receiveCar (Car car){
        carService.saveReceiveCar(car);
        log.info("received a car : " + car.getCarId() + " " + car.getModel() + " " + car.getPersonId());
    }
}
