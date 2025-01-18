package org.spring.cassandra.springcassandra.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cassandra.springcassandra.entity.Person;
import org.spring.cassandra.springcassandra.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Person addPersonData(Person person) {
        LOG.info("saving data to db");
        Person person1 = personRepository.save(person);
        LOG.info("Successfully save data in database");
        return person1;
    }

    public List<Person> fetchData() {
        return personRepository.findAll().stream().sorted(Comparator.comparing(Person::getId)).toList();
    }

    public Optional<Person> fetchPersonData(int id) {
        return personRepository.findById(id);
    }
}
