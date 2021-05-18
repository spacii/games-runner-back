package com.api.Controllers;

import com.api.Entities.Game;
import com.api.Entities.Review;
import com.api.Entities.Score;
import com.api.Entities.User;
import com.api.Services.GameService;
import com.api.Services.ReviewService;
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
    private ScoreService scoreService;

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @CrossOrigin
    @GetMapping("/scores")
    public CollectionModel<EntityModel<Score>> getScores(){
        return scoreService.getScores();
    }

    @CrossOrigin
    @GetMapping("/scores/{scoreId}")
    public EntityModel<Score> getScoreById(@PathVariable Long scoreId){
        return scoreService.getScoreById(scoreId);
    }

    @CrossOrigin
    @GetMapping("/scores/{scoreId}/game")
    public EntityModel<Game> getScoreGame(@PathVariable Long scoreId){
        return gameService.getGameByScore(scoreId);
    }

    @CrossOrigin
    @GetMapping("/scores/{scoreId}/user")
    public EntityModel<User> getScoreUser(@PathVariable Long scoreId) {
        return userService.getUserByScore(scoreId);
    }

    @CrossOrigin
    @PostMapping("/scores")
    public EntityModel<Score> addScore(@RequestBody Score newScore, @RequestParam Long gameId, @RequestParam Long userId){
        return scoreService.addScore(newScore, gameId, userId);
    }

    @CrossOrigin
    @PutMapping("/scores/{scoreId}")
    public EntityModel<Score> updateScore(@RequestBody Score newScore, @PathVariable Long scoreId){
        return scoreService.updateScore(newScore, scoreId);
    }

    @CrossOrigin
    @DeleteMapping("/scores/{scoreId}")
    public void deleteScore(@PathVariable Long scoreId){
        scoreService.deleteScore(scoreId);
    }

    @CrossOrigin
    @PostMapping("/scores/{scoreId}/review")
    public EntityModel<Review> addReview(@RequestBody Review newReview, @PathVariable Long scoreId){
        return reviewService.addReview(newReview, scoreId);
    }
}
