package com.api.Services;

import com.api.Controllers.GameController;
import com.api.Controllers.ScoreController;
import com.api.Controllers.UserController;
import com.api.Entities.Game;
import com.api.Entities.Review;
import com.api.Entities.Score;
import com.api.Entities.User;
import com.api.Exceptions.NotFoundException;
import com.api.ModelAssemblers.ScoreModelAssembler;
import com.api.Repositories.GameRepository;
import com.api.Repositories.ReviewRepository;
import com.api.Repositories.ScoreRepository;
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
public class ScoreService {
    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScoreModelAssembler scoreModelAssembler;

    public CollectionModel<EntityModel<Score>> getScores(){
        List<EntityModel<Score>> scores = scoreRepository.findAll()
                .stream()
                .map(scoreModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                scores,
                linkTo(methodOn(ScoreController.class).getScores()).withSelfRel()
        );
    }

    public EntityModel<Score> getScoreById(Long scoreId){
        Score score = scoreRepository.findById(scoreId).orElseThrow(() -> new NotFoundException("score", scoreId));
        return scoreModelAssembler.toModel(score);
    }

    public CollectionModel<EntityModel<Score>> getScoresByGameId(Long gameId){
        List<EntityModel<Score>> scores = scoreRepository.findAllByGameGameId(gameId)
                .stream()
                .map(scoreModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                scores,
                linkTo(methodOn(GameController.class).getGameScores(gameId)).withSelfRel()
        );
    }

    public CollectionModel<EntityModel<Score>> getScoresByUserId(Long userId){
        List<EntityModel<Score>> scores = scoreRepository.findAllByUserUserId(userId)
                .stream()
                .map(scoreModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                scores,
                linkTo(methodOn(UserController.class).getUserScores(userId)).withSelfRel()
        );
    }

    public EntityModel<Score> addScore(Score newScore, Long gameId, Long userId){
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException("game", gameId));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user", userId));

        newScore.setGame(game);
        newScore.setUser(user);

        Score score = scoreRepository.save(newScore);
        return scoreModelAssembler.toModel(score);
    }

    public EntityModel<Score> updateScore(Score newScore, Long scoreId){
        Score score = scoreRepository.findById(scoreId).orElseThrow(() -> new NotFoundException("score", scoreId));
        score.setScore(newScore.getScore());

        return scoreModelAssembler.toModel(scoreRepository.save(score));
    }

    public void deleteScore(Long scoreId){
        scoreRepository.deleteById(scoreId);
    }
}
