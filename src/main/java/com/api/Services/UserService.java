package com.api.Services;

import com.api.Controllers.UserController;
import com.api.Entities.User;
import com.api.Exceptions.NotFoundException;
import com.api.ModelAssemblers.UserModelAssembler;
import com.api.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserModelAssembler userModelAssembler;

    public CollectionModel<EntityModel<User>> getUsers(){
        List<EntityModel<User>> users = userRepository.findAll()
                .stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                users,
                linkTo(methodOn(UserController.class).getUsers()).withSelfRel()
        );
    }

    public EntityModel<User> getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user", id));
        return userModelAssembler.toModel(user);
    }

    public EntityModel<User> addNewUser(User newUser){
        User user = userRepository.save(newUser);
        return  userModelAssembler.toModel(user);
    }

    public EntityModel<User> getUserByScore(Long id){
        User user = userRepository.findByScoresScoreId(id);
        return userModelAssembler.toModel(user);
    }
}