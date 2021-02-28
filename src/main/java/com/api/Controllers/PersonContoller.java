package com.api.Controllers;

import com.api.Entities.Person;
import com.api.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PersonContoller {
    @Autowired
    PersonService personService;

    @CrossOrigin
    @GetMapping("/persons")
    public CollectionModel<EntityModel<Person>> getPersons(){
        return personService.getPersons();
    }

    @CrossOrigin
    @GetMapping("/persons/{id}")
    public EntityModel<Person> getPersonById(@PathVariable Long id){
        return personService.getPersonById(id);
    }
}
