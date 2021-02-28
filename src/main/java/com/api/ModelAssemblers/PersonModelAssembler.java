package com.api.ModelAssemblers;

import com.api.Controllers.PersonContoller;
import com.api.Entities.Person;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {
    @Override
    public EntityModel<Person> toModel(Person person) {
        return EntityModel.of(
                person,
                linkTo(methodOn(PersonContoller.class).getPersonById(person.getPersonId())).withSelfRel(),
                linkTo(methodOn(PersonContoller.class).getPersons()).withRel("persons")
        );
    }
}
