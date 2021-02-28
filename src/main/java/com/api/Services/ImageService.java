package com.api.Services;

import com.api.Controllers.GameController;
import com.api.Controllers.ImageController;
import com.api.Entities.Image;
import com.api.Exceptions.NotFoundException;
import com.api.ModelAssemblers.ImageModelAssembler;
import com.api.Repositories.ImageRepository;
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
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageModelAssembler imageModelAssembler;

    public CollectionModel<EntityModel<Image>> getImages(){
        List<EntityModel<Image>> images = imageRepository.findAll()
                .stream()
                .map(imageModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                images,
                linkTo(methodOn(ImageController.class).getImages()).withSelfRel()
        );
    }

    public EntityModel<Image> getImageById(Long id){
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("image", id));
        return imageModelAssembler.toModel(image);
    }

    public CollectionModel<EntityModel<Image>> getImagesByGameId(Long id){
        List<EntityModel<Image>> images = imageRepository.findAllByGameGameId(id)
                .stream()
                .map(imageModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                images,
                linkTo(methodOn(GameController.class).getGameImages(id)).withSelfRel()
        );
    }

    public CollectionModel<EntityModel<Image>> getScreenshotsByGameId(Long id){
        List<EntityModel<Image>> images = imageRepository.findAllByImageTypeAndGameGameId("screenshot", id)
                .stream()
                .map(imageModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                images,
                linkTo(methodOn(GameController.class).getGameImages(id)).withSelfRel()
        );
    }

    public EntityModel<Image> getTitleByGameId(Long id){
        Image title = imageRepository.findOneByImageTypeAndGameGameId("title", id);
        return imageModelAssembler.toModel(title);
    }

    public EntityModel<Image> getPosterByGameId(Long id){
        Image poster = imageRepository.findOneByImageTypeAndGameGameId("poster", id);
        return imageModelAssembler.toModel(poster);
    }
}
