package org.spring.cassandra.springcassandra.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cassandra.springcassandra.entity.Person;
import org.spring.cassandra.springcassandra.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cassandra/v1")
public class PersonController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);


    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/addPersonData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> savePerson(HttpServletRequest request, @RequestBody Person person) {
        LOG.info("creating person data : {}", request.getRemoteAddr());
        Person personData = personService.addPersonData(person);
        LOG.info("adding person data {} : ", personData);
        return new ResponseEntity<>(personData, HttpStatus.OK);
    }


    @GetMapping(value = "/getAllData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getAllPerson() {
        LOG.info("Fetching all person data");
        List<Person> personList = personService.fetchData();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping(value = "/getPersonData/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Person>> getAllPerson(@PathVariable(value = "id") int id) {
        LOG.info("Fetching all person data");
        Optional<Person> person = personService.fetchPersonData(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
