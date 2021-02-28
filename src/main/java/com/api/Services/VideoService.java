package com.api.Services;

import com.api.Controllers.GameController;
import com.api.Controllers.VideoController;
import com.api.Entities.Video;
import com.api.Exceptions.NotFoundException;
import com.api.ModelAssemblers.VideoModelAssembler;
import com.api.Repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoModelAssembler videoModelAssembler;

    public CollectionModel<EntityModel<Video>> getVideos(){
        List<EntityModel<Video>> videos = videoRepository.findAll()
                .stream()
                .map(videoModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                videos,
                linkTo(methodOn(VideoController.class).getVideos()).withSelfRel()
        );
    }

    public EntityModel<Video> getVideoById(Long id){
        Video video = videoRepository.findById(id).orElseThrow(() -> new NotFoundException("video", id));
        return videoModelAssembler.toModel(video);
    }

    public CollectionModel<EntityModel<Video>> getVideosByGameId(Long id){
        List<EntityModel<Video>> videos = videoRepository.findAllByGameGameId(id)
                .stream()
                .map(videoModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                videos,
                linkTo(methodOn(GameController.class).getGameVideos(id)).withSelfRel()
        );
    }
}
