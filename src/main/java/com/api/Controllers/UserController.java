package com.api.Controllers;

import com.api.Entities.User;
import com.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> getUsers(){
        return userService.getUsers();
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @CrossOrigin
    @PostMapping("/users")
    public EntityModel<User> addNewUser(@RequestBody User newUser){
        return userService.addNewUser(newUser);
    }
}