package com.api.ModelAssemblers;

import com.api.Controllers.VideoController;
import com.api.Entities.Video;
import com.api.resources.ResourcesController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VideoModelAssembler implements RepresentationModelAssembler<Video, EntityModel<Video>> {
    @Override
    public EntityModel<Video> toModel(Video video) {

        return EntityModel.of(
                video,
                linkTo(methodOn(VideoController.class).getVideoById(video.getVideoId())).withSelfRel(),
                linkTo(methodOn(VideoController.class).getVideos()).withRel("videos"),
                linkTo(methodOn(VideoController.class).getVideoByIdGame(video.getVideoId())).withRel("game"),
                linkTo(methodOn(ResourcesController.class).getVideo(null, "mp4",video.getVideoName())).withRel("resource")
        );
    }
}
