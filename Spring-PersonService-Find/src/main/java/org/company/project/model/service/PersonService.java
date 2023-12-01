package org.company.project.model.service;

import org.company.project.common.exception.UserNotFoundException;
import org.company.project.model.domain.Person;
import org.company.project.model.repository.Crud;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Scope("prototype")
public class PersonService {

    public Person findPersonById (Person person) throws Exception {
        try (Crud crud = new Crud()) {
           return crud.findPersonById(person);
        }
    }

    public void saveReceivedPerson (Person person) throws Exception {
        try (Crud crud = new Crud()){
            crud.saveReceivedPerson(person);
            crud.commit();
        }
    }
}
