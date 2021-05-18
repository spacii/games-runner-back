package com.api.ModelAssemblers;

import com.api.Controllers.ReviewController;
import com.api.Entities.Review;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReviewModelAssembler implements RepresentationModelAssembler<Review, EntityModel<Review>> {
    @Override
    public EntityModel<Review> toModel(Review review) {
        return EntityModel.of(
                review,
                linkTo(methodOn(ReviewController.class).getReviewUser(review.getReviewId())).withRel("user")
        );
    }
}
