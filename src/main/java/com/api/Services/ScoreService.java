package com.api.Services;

import com.api.Controllers.GameController;
import com.api.Controllers.ScoreController;
import com.api.Entities.Game;
import com.api.Entities.Score;
import com.api.ModelAssemblers.ScoreModelAssembler;
import com.api.Repositories.ScoreRepository;
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
}
