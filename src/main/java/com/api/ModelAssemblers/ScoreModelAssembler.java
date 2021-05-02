package com.api.ModelAssemblers;

import com.api.Controllers.ScoreController;
import com.api.Entities.Score;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ScoreModelAssembler implements RepresentationModelAssembler<Score, EntityModel<Score>> {

    @Override
    public EntityModel<Score> toModel(Score score) {
        return EntityModel.of(
                score,
                linkTo(methodOn(ScoreController.class).getScores()).withRel("scores"),
                linkTo(methodOn(ScoreController.class).getScoreGame(score.getScoreId())).withRel("game"),
                linkTo(methodOn(ScoreController.class).getScoreUser(score.getScoreId())).withRel("user")
        );
    }
}
