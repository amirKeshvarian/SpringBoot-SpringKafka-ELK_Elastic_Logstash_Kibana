package org.company.project.api;

import lombok.extern.slf4j.Slf4j;
import org.company.project.model.domain.Person;
import org.company.project.model.service.PersonService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("singleton")
@RequestMapping("/person")
@Slf4j
public class PersonAPI {
    private final PersonService personService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PersonAPI(PersonService personService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.personService = personService;
        this.kafkaTemplate = kafkaTemplate;
    }
    @GetMapping("/save.do")
    public ResponseEntity save (@ModelAttribute Person person) throws Exception {
        Person person1 = personService.save(person);
        kafkaTemplate.send("person-event", person1);
        return new ResponseEntity(HttpStatus.OK);
    }
    @KafkaListener(topics = "person-event")
    public void receivedPerson (Person person) throws Exception {
        personService.saveFromKafka(person);
    }


}
