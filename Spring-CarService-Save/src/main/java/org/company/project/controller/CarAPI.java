package org.company.project.controller;

import org.company.project.model.domain.Car;
import org.company.project.model.service.CarService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("singleton")
@RequestMapping("/car")
public class CarAPI {
    private final CarService carService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CarAPI(CarService carService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.carService = carService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/save.do")
    public ResponseEntity save (@ModelAttribute Car car) throws Exception {
        if (car.getPersonId() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else {
            Car saveCar = carService.save(car);
            kafkaTemplate.send("car-event", saveCar);
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
