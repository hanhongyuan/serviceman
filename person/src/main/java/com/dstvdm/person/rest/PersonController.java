package com.dstvdm.person.rest;

/**
 * Created by pscot on 3/7/2016.
 */

import com.dstvdm.person.model.Person;
import com.dstvdm.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pscot on 3/2/2016.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Person> findAllPersons() {
        return repository.findAll();
    }

    @RequestMapping("/findByFirstName")
    public List<Person> findByFirstName(@RequestParam String firstName) {
        return repository.findByFirstName(firstName);
    }

    @RequestMapping("/findByLastName")
    public List<Person> findByLastName(@RequestParam String lastName) {
        return repository.findByLastName(lastName);
    }

    @RequestMapping("/findByEmail")
    public List<Person> findByEmail(@RequestParam String email) {
        return repository.findByEmailAddress(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addPerson(@RequestBody Person person) {
        return repository.addPerson(person);
    }


}