package com.api.Controllers;

import com.api.Entities.Score;
import com.api.Entities.User;
import com.api.Services.ScoreService;
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

    @Autowired
    ScoreService scoreService;

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
    public EntityModel<User> addUser(@RequestBody User newUser){
        return userService.addUser(newUser);
    }

    @CrossOrigin
    @GetMapping("/users/{id}/scores")
    public CollectionModel<EntityModel<Score>> getUserScores(@PathVariable Long id){
        return scoreService.getScoresByUserId(id);
    }
}