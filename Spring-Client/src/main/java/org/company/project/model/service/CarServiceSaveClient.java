package org.company.project.model.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("car-service-save")
public interface CarServiceSaveClient {
    @GetMapping("/car/save.do")
    ResponseEntity saveCar (@RequestParam("model")String model, @RequestParam("personId")Long personId);

}
