package org.company.project.model.service;

import org.company.project.model.domain.Car;
import org.company.project.model.domain.Person;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class ServiceClient {
    private final PersonServiceSaveClient personServiceSaveClient;
    private final  CarServiceSaveClient carServiceSaveClient;
    private final PersonServiceFindClient personServiceFindClient;
    private final CarServiceFindClient carServiceFindClient;
    private final SequenceGenericServiceClient sequenceGenericServiceClient;

    public ServiceClient(PersonServiceSaveClient personServiceSaveClient, CarServiceSaveClient carServiceSaveClient, PersonServiceFindClient personServiceFindClient, CarServiceFindClient carServiceFindClient, SequenceGenericServiceClient sequenceGenericServiceClient) {
        this.personServiceSaveClient = personServiceSaveClient;
        this.carServiceSaveClient = carServiceSaveClient;
        this.personServiceFindClient = personServiceFindClient;
        this.carServiceFindClient = carServiceFindClient;
        this.sequenceGenericServiceClient = sequenceGenericServiceClient;
    }
    public ResponseEntity savePerson (Person person) {
        return personServiceSaveClient.savePerson(person.getName(), person.getFamily());
    }
    public ResponseEntity saveCar (Car car) {
        return carServiceSaveClient.saveCar(car.getModel(), car.getPersonId());
    }
    public ResponseEntity findPersonById(Person person){
        return personServiceFindClient.findPersonById(String.valueOf(person.getPersonId()));
    }
    public ResponseEntity findCarsByPersonId (Long personId){
        return carServiceFindClient.findCarsByPersonId(String.valueOf(personId));
    }
    public ResponseEntity<Long> nextVal () {
        return sequenceGenericServiceClient.nextVal();
    }
}
