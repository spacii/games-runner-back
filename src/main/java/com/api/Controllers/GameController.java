package com.api.Controllers;

import com.api.Entities.Game;
import com.api.Entities.Image;
import com.api.Entities.Person;
import com.api.Entities.Video;
import com.api.Services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {

    Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    GameService gameService;

    @Autowired
    VideoService videoService;

    @Autowired
    ImageService imageService;

    @Autowired
    FranchiseService franchiseService;

    @Autowired
    PersonService personService;

    @CrossOrigin
    @GetMapping("/games")
    public CollectionModel<EntityModel<Game>> getGames(){
       return gameService.getGames();
    }

    @CrossOrigin
    @GetMapping("/games/{id}")
    public EntityModel<Game> getGameById(@PathVariable Long id){
        return gameService.getGameById(id);
    }

    @CrossOrigin
    @GetMapping("/games/{id}/videos")
    public CollectionModel<EntityModel<Video>> getGameVideos(@PathVariable Long id){
        return videoService.getVideosByGameId(id);
    }

    @CrossOrigin
    @GetMapping("/games/{id}/images")
    public CollectionModel<EntityModel<Image>> getGameImages(@PathVariable Long id){
        return imageService.getImagesByGameId(id);
    }

    @CrossOrigin
    @GetMapping("/games/{id}/screenshots")
    public CollectionModel<EntityModel<Image>> getGameScreenshots(@PathVariable Long id){
        return imageService.getScreenshotsByGameId(id);
    }

    @CrossOrigin
    @GetMapping("/games/{id}/title")
    public EntityModel<Image> getGameTitle(@PathVariable Long id){
        return imageService.getTitleByGameId(id);
    }

    @CrossOrigin
    @GetMapping("/games/{id}/poster")
    public EntityModel<Image> getGamePoster(@PathVariable Long id){
        return imageService.getPosterByGameId(id);
    }

    @CrossOrigin
    @GetMapping("/games/{id}/franchise")
    public CollectionModel<EntityModel<Game>> getGameFranchise(@PathVariable Long id){
        return gameService.getGamesFromFranchiseByGameId(id);
    }

    @CrossOrigin
    @GetMapping("/games/{id}/persons")
    public CollectionModel<EntityModel<Person>> getGamePersons(@PathVariable Long id){
        return personService.getPersonsByGameId(id);
    }
}
