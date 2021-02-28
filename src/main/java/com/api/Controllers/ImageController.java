package com.api.Controllers;

import com.api.Entities.Game;
import com.api.Entities.Image;
import com.api.Services.GameService;
import com.api.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://games-runner-front-end.herokuapp.com/")
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    GameService gameService;

    @GetMapping("/images")
    public CollectionModel<EntityModel<Image>> getImages(){
        return imageService.getImages();
    }

    @GetMapping("/images/{id}")
    public EntityModel<Image> getImageById(@PathVariable Long id){
        return imageService.getImageById(id);
    }

    @GetMapping("/images/{id}/game")
    public EntityModel<Game> getImageByIdGame(@PathVariable Long id){
        return gameService.getGameByImageId(id);
    }
}
