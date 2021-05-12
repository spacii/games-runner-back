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

    public EntityModel<Score> getScoreById(Long id){
        Score score = scoreRepository.findById(id).orElseThrow(() -> new NotFoundException("score", id));
        return scoreModelAssembler.toModel(score);
    }

    public CollectionModel<EntityModel<Score>> getScoresByGameId(Long id){
        List<EntityModel<Score>> scores = scoreRepository.findAllByGameGameId(id)
                .stream()
                .map(scoreModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                scores,
                linkTo(methodOn(GameController.class).getGameScores(id)).withSelfRel()
        );
    }

    public CollectionModel<EntityModel<Score>> getScoresByUserId(Long id){
        List<EntityModel<Score>> scores = scoreRepository.findAllByUserUserId(id)
                .stream()
                .map(scoreModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                scores,
                linkTo(methodOn(UserController.class).getUserScores(id)).withSelfRel()
        );
    }

    public EntityModel<Score> addScore(Score newScore, Long gameid, Long userid){
        Game game = gameRepository.findById(gameid).orElseThrow(() -> new NotFoundException("game", gameid));
        User user = userRepository.findById(userid).orElseThrow(() -> new NotFoundException("user", userid));

        newScore.setGame(game);
        newScore.setUser(user);

        Score score = scoreRepository.save(newScore);
        return scoreModelAssembler.toModel(score);
    }

    public EntityModel<Score> updateScore(Score newScore, Long id){
        Score score = scoreRepository.findById(id).orElseThrow(() -> new NotFoundException("score", id));
        score.setScore(newScore.getScore());

        return scoreModelAssembler.toModel(scoreRepository.save(score));
    }

    public void deleteScore(Long id){
        Score score = scoreRepository.findById(id).orElseThrow(() -> new NotFoundException("score", id));
        scoreRepository.delete(score);

        Review review = reviewRepository.findByGameGameIdAndUserUserId(score.getGame().getGameId(), score.getUser().getUserId())
                .orElseThrow(() -> new NotFoundException("ff", id)); // TODO
        reviewRepository.delete(review);
    }
}
