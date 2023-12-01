package org.company.project.controller;

import org.company.project.model.domain.Car;
import org.company.project.model.domain.Person;
import org.company.project.model.service.ServiceClient;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("singleton")
@RequestMapping("/client")
public class ReadServiceController {
    private final ServiceClient serviceClient;

    public ReadServiceController(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    @GetMapping("/savePerson.do")
    public ResponseEntity savePerson(@RequestParam("name") String name, @RequestParam("family") String family) {
        return serviceClient.savePerson(new Person().setName(name).setFamily(family));
    }

    @GetMapping("/saveCar.do")
    public ResponseEntity saveCar(@RequestParam("model") String model, @RequestParam("personId") String personId) {
        return serviceClient.saveCar(new Car().setModel(model).setPersonId(Long.parseLong(personId)));
    }

    @GetMapping("/findPersonById.do")
    public ResponseEntity findPersonById (@RequestParam("personId")String personId){
        return serviceClient.findPersonById(new Person().setPersonId(Long.parseLong(personId)));
    }

    @GetMapping("/findCarsByPersonId.do")
    public ResponseEntity findCarsByPersonId (@RequestParam("personId") String personId){
        return serviceClient.findCarsByPersonId(Long.parseLong(personId));
    }

    @GetMapping("/nextVal.do")
    public ResponseEntity<Long> nextVal () {
        return serviceClient.nextVal();
    }
}
