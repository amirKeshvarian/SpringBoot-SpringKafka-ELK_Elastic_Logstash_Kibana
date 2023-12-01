package org.company.project.model.service;

import org.company.project.model.domain.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("car-service-find")
public interface CarServiceFindClient {
    @GetMapping("/car/findCarById.do")
    ResponseEntity<Car> findCarById (@RequestParam("carId")String carId);
    @GetMapping("/car/findCarsByPersonId.do")
    ResponseEntity<List<Car>> findCarsByPersonId (@RequestParam("personId")String personId);
}
