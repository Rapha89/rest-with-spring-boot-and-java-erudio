package br.com.rapha89.services;

import br.com.rapha89.controllers.TestLogController;
import br.com.rapha89.data.dto.v1.PersonDTO;
import br.com.rapha89.data.dto.v2.PersonDTOV2;
import br.com.rapha89.exception.ResourceNotFoundException;
import static br.com.rapha89.mapper.ObjectMapper.parseListObjects;
import static br.com.rapha89.mapper.ObjectMapper.parseObject;

import br.com.rapha89.mapper.custom.PersonMapper;
import br.com.rapha89.model.Person;
import br.com.rapha89.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;


    public List<PersonDTO> findAll(){
        logger.info("Finding all people");
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id){
        logger.info("Finding one person");
        var entity = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No records found for this Id!"));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Creating one Person");
        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person){
        logger.info("Creating one Person");
        var entity = converter.convertDTOToEntity(person);

        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Updating one Person");
        Person entity = repository.findById(person.getId()).orElseThrow( () -> new ResourceNotFoundException("No records found for this Id!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting one person");
        Person entity = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No records found for this Id!"));
        repository.delete(entity);
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
