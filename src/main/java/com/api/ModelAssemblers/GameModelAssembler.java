package com.api.ModelAssemblers;

import com.api.Components.RatingCounter;
import com.api.Controllers.GameController;
import com.api.Entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GameModelAssembler implements RepresentationModelAssembler<Game, EntityModel<Game>> {
    @Autowired
    RatingCounter ratingCounter;

    @Override
    public EntityModel<Game> toModel(Game game) {
        game.setRating(ratingCounter.getRating(game.getScores()));
        game.setRatingCounts(game.getScores().size());

        return EntityModel.of(
                game,
                linkTo(methodOn(GameController.class).getGameById(game.getGameId())).withSelfRel(),
                linkTo(methodOn(GameController.class).getGames()).withRel("games"),
                linkTo(methodOn(GameController.class).getGameVideos(game.getGameId())).withRel("videos"),
                linkTo(methodOn(GameController.class).getGameImages(game.getGameId())).withRel("images"),
                linkTo(methodOn(GameController.class).getGameScreenshots(game.getGameId())).withRel("screenshots"),
                linkTo(methodOn(GameController.class).getGameTitle(game.getGameId())).withRel("title"),
                linkTo(methodOn(GameController.class).getGamePoster(game.getGameId())).withRel("poster"),
                linkTo(methodOn(GameController.class).getGameFranchise(game.getGameId())).withRel("franchise"),
                linkTo(methodOn(GameController.class).getGamePersons(game.getGameId())).withRel("persons"),
                linkTo(methodOn(GameController.class).getGameScores(game.getGameId())).withRel("scores"),
                linkTo(methodOn(GameController.class).getGameReviews(game.getGameId())).withRel("reviews")
        );
    }
}
