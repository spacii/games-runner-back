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
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    GameService gameService;

    @CrossOrigin
    @GetMapping("/images")
    public CollectionModel<EntityModel<Image>> getImages(){
        return imageService.getImages();
    }

    @CrossOrigin
    @GetMapping("/images/{id}")
    public EntityModel<Image> getImageById(@PathVariable Long id){
        return imageService.getImageById(id);
    }

    @CrossOrigin
    @GetMapping("/images/{id}/game")
    public EntityModel<Game> getImageByIdGame(@PathVariable Long id){
        return gameService.getGameByImageId(id);
    }
}
