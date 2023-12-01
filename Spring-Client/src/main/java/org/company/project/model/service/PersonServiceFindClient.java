package org.company.project.model.service;

import org.company.project.model.domain.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("person-service-find")
public interface PersonServiceFindClient {
    @GetMapping("/person/findPersonById.do")
    ResponseEntity<Person> findPersonById (@RequestParam("personId")String personId);
}
