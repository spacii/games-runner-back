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
public class VideoController {
    @Autowired
    VideoService videoService;

    @Autowired
    GameService gameService;

    @CrossOrigin
    @GetMapping("/videos")
    public CollectionModel<EntityModel<Video>> getVideos(){
        return videoService.getVideos();
    }

    @CrossOrigin
    @GetMapping("/videos/{id}")
    public EntityModel<Video> getVideoById(@PathVariable Long id){
        return videoService.getVideoById(id);
    }

    @CrossOrigin
    @GetMapping("/videos/{id}/game")
    public EntityModel<Game> getVideoByIdGame(@PathVariable Long id){
        return gameService.getGameByVideoId(id);
    }
}