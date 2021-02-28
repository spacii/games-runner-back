package com.api.ModelAssemblers;

import com.api.Controllers.ImageController;
import com.api.Entities.Image;
import com.api.resources.ResourcesController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ImageModelAssembler implements RepresentationModelAssembler<Image, EntityModel<Image>> {
    @Override
    public EntityModel<Image> toModel(Image image) {
        Link link = Link.valueOf("https://games-runner-media.s3.us-east-2.amazonaws.com/"+image.getImageType()+"/"+ image.getImageName() + "." + image.getImageFormat());
        return EntityModel.of(
                image,
                linkTo(methodOn(ImageController.class).getImageById(image.getImageId())).withSelfRel(),
                linkTo(methodOn(ImageController.class).getImages()).withRel("images"),
                linkTo(methodOn(ImageController.class).getImageByIdGame(image.getImageId())).withRel("game"),
                link
                //linkTo(methodOn(ResourcesController.class).getImage(image.getImageType(), image.getImageFormat(), image.getImageName())).withRel("resource")
                //linkTo("https://games-runner-media.s3.us-east-2.amazonaws.com/"+image.getImageType()+"/"+ image.getImageName() +"." + image.getImageFormat()).withRel("resource")
        );
    }
}
