package com.api.Services;

import com.api.Controllers.GameController;
import com.api.Controllers.PersonContoller;
import com.api.Entities.Person;
import com.api.ModelAssemblers.GameModelAssembler;
import com.api.ModelAssemblers.PersonModelAssembler;
import com.api.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonModelAssembler personModelAssembler;

    @Autowired
    GameModelAssembler gameModelAssembler;

    public CollectionModel<EntityModel<Person>> getPersons(){
        List<EntityModel<Person>> persons = personRepository.findAll()
                .stream()
                .map(personModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                persons,
                linkTo(methodOn(PersonContoller.class).getPersons()).withSelfRel()
        );
    }

    public EntityModel<Person> getPersonById(Long id){
        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("fuck"));
        return personModelAssembler.toModel(person);
    }

    public CollectionModel<EntityModel<Person>> getPersonsByGameId(Long id){
        List<EntityModel<Person>> persons = personRepository.findAllByGamesGameId(id)
                .stream()
                .map(personModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                persons,
                linkTo(methodOn(GameController.class).getGamePersons(id)).withSelfRel()
        );
    }

//    public CollectionModel<EntityModel<Game>> getGamesByPersonId(Long id){
//        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("fuck"));
//
//        List<EntityModel<Game>> games = person.getGames()
//                .stream()
//                .map(gameModelAssembler::toModel)
//                .collect(Collectors.toList());
//
//        return CollectionModel.of(
//                games,
//                linkTo(methodOn(PersonContoller.class).getPersons()).withSelfRel()
//        );
//    }
}
