package org.company.project.model.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("person-service-save")
public interface PersonServiceSaveClient {
    @GetMapping("/person/save.do")
    ResponseEntity savePerson (@RequestParam("name") String name,@RequestParam("family") String family);
}
