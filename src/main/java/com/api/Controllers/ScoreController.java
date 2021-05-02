package com.api.Controllers;

import com.api.Entities.Game;
import com.api.Entities.Score;
import com.api.Entities.User;
import com.api.Services.GameService;
import com.api.Services.ScoreService;
import com.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScoreController {
    @Autowired
    ScoreService scoreService;

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/scores")
    public CollectionModel<EntityModel<Score>> getScores(){
        return scoreService.getScores();
    }

    @CrossOrigin
    @GetMapping("/scores/{id}/game")
    public EntityModel<Game> getScoreGame(@PathVariable Long id){
        return gameService.getGameByScore(id);
    }

    @CrossOrigin
    @GetMapping("/scores/{id}/user")
    public EntityModel<User> getScoreUser(@PathVariable Long id) {
        return userService.getUserByScore(id);
    }

//    @CrossOrigin
//    @GetMapping("/scores/game/{id}")
//    public CollectionModel<EntityModel<Score>> getScoresByGame(@PathVariable Long id){
//
//    }
}
