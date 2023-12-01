package org.company.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.company.project.common.exception.UserNotFoundException;
import org.company.project.model.domain.Person;
import org.company.project.model.service.PersonService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@Scope("prototype")
@RequestMapping("/person")
@Slf4j
public class PersonAPI {
    private final PersonService personService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PersonAPI(PersonService personService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.personService = personService;
        this.kafkaTemplate = kafkaTemplate;
    }
    @GetMapping("/findPersonById.do")
    public ResponseEntity<Person> findPersonById (@RequestParam("personId") String personId) throws Exception {
        Person person = personService.findPersonById(new Person().setPersonId(Long.parseLong(personId)));
        return  ResponseEntity.status(HttpStatus.OK).body(person);
    }
    @KafkaListener(topics = "person-event")
    public void receivedPerson (Person person) throws Exception {
        personService.saveReceivedPerson(person);
        log.info("received a person : " + person.getPersonId() + " " + person.getName() + " " +person.getFamily());
    }

}
