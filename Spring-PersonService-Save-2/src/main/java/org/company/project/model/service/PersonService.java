package org.company.project.model.service;

import jakarta.transaction.RollbackException;
import lombok.extern.slf4j.Slf4j;
import org.company.project.model.domain.Person;
import org.company.project.model.repository.PersonRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Scope("singleton")
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;
    private static final List<Long> personIdList = new ArrayList<>();//because this microservice do not insert the record that own send to kafka

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(rollbackFor = RollbackException.class)
    public Person save (Person person) throws Exception {
        Person savePerson = personRepository.save(person);
        Collections.synchronizedList(personIdList).add(savePerson.getPersonId());
        return savePerson;
    }
    @Transactional
    public void saveFromKafka (Person person) {
        if (!Collections.synchronizedList(personIdList).contains(person.getPersonId())){
            personRepository.saveWithoutMerge(person);
            Collections.synchronizedList(personIdList).add(person.getPersonId());
            log.info("save person from kafka : " + person.getPersonId() + " " + person.getName() + " " + person.getFamily());
        }
    }
}
