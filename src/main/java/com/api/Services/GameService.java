package com.api.Services;

import com.api.Controllers.GameController;
import com.api.Entities.Game;
import com.api.Exceptions.GameNotFoundException;
import com.api.ModelAssemblers.GameModelAssembler;
import com.api.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameModelAssembler gameModelAssembler;

    public CollectionModel<EntityModel<Game>> getGames(){
        List<EntityModel<Game>> games = gameRepository.findAll()
                .stream()
                .map(gameModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                games,
                linkTo(methodOn(GameController.class).getGames()).withSelfRel()
        );
    }

    public EntityModel<Game> getGameById(Long id){
        //Game game = gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("fuck"));
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        return gameModelAssembler.toModel(game);
    }

    public EntityModel<Game> getGameByImageId(Long id){
        Game game = gameRepository.findByImagesImageId(id);
        return gameModelAssembler.toModel(game);
    }

    public EntityModel<Game> getGameByVideoId(Long id){
        Game game = gameRepository.findByVideosVideoId(id);
        return gameModelAssembler.toModel(game);
    }

    public CollectionModel<EntityModel<Game>> getGamesFromFranchiseByGameId(Long id){
        List<EntityModel<Game>> games = gameRepository.findAllByFranchiseGameGameIdAndGameIdNot(id, id)
                .stream()
                .map(gameModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                games,
                linkTo(methodOn(GameController.class).getGames()).withSelfRel()
        );
    }
}
