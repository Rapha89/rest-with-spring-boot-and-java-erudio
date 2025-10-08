package br.com.erudio.services;

import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll(){
        logger.info("Finding all people");
        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            persons.add(mockPerson(i));
        }
        return persons;
    }

    public Person findById(String id){
        logger.info("Finding one person");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Raphael");
        person.setLastName("Vacari");
        person.setAddress("Mauá - São Paulo - Brasil");
        person.setGender("Male");
        return person;
    }

    public Person create(Person person){
        logger.info("Creating one Person");
        return person;
    }

    public Person update(Person person){
        logger.info("Updating one Person");
        return person;
    }

    public void delete(String id){
        logger.info("Deleting one person");
    }

    private Person mockPerson(int i){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Firstname " + i);
        person.setLastName("Lastname");
        person.setAddress("Some Address in Brasil");
        person.setGender("Male");
        return person;
    }




}
