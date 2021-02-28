package com.api.Controllers;

import com.api.Entities.Game;
import com.api.Entities.Video;
import com.api.Services.GameService;
import com.api.Services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://games-runner-front-end.herokuapp.com/")
public class VideoController {
    @Autowired
    VideoService videoService;

    @Autowired
    GameService gameService;

    @GetMapping("/videos")
    public CollectionModel<EntityModel<Video>> getVideos(){
        return videoService.getVideos();
    }

    @GetMapping("/videos/{id}")
    public EntityModel<Video> getVideoById(@PathVariable Long id){
        return videoService.getVideoById(id);
    }

    @GetMapping("/videos/{id}/game")
    public EntityModel<Game> getVideoByIdGame(@PathVariable Long id){
        return gameService.getGameByVideoId(id);
    }
}