package com.api.Services;

import com.api.Controllers.ReviewController;
import com.api.Entities.Game;
import com.api.Entities.Review;
import com.api.Entities.Score;
import com.api.Entities.User;
import com.api.Exceptions.NotFoundException;
import com.api.ModelAssemblers.ReviewModelAssembler;
import com.api.Repositories.GameRepository;
import com.api.Repositories.ReviewRepository;
import com.api.Repositories.ScoreRepository;
import com.api.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewModelAssembler reviewModelAssembler;

    public CollectionModel<EntityModel<Review>> getReviews(){
        List<EntityModel<Review>> reviews = reviewRepository.findAll()
                .stream()
                .map(reviewModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reviews,
                linkTo(methodOn(ReviewController.class).getReviews()).withSelfRel()
        );
    }

    public EntityModel<Review> getReviewById(Long id){
        Review review = reviewRepository.findById(id).orElseThrow(() -> new NotFoundException("review", id));
        return reviewModelAssembler.toModel(review);
    }

    public EntityModel<Review> addReview(Review newReview, Long scoreId){
        Score score = scoreRepository.findById(scoreId).orElseThrow(() -> new NotFoundException("score", scoreId));
        newReview.setScore(score);
        return reviewModelAssembler.toModel(reviewRepository.save(newReview));
    }

    public EntityModel<Review> updateReview(Review newReview, Long reviewId){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException("review", reviewId));
        review.setReviewText(newReview.getReviewText());

        return reviewModelAssembler.toModel(reviewRepository.save(review));
    }

    public void deleteReview(Long reviewId){
        reviewRepository.deleteById(reviewId);
    }

    public CollectionModel<EntityModel<Review>> getReviewsByGameId(Long gameId){
        List<EntityModel<Review>> reviews = reviewRepository.findAllByScoreGameGameId(gameId)
                .stream()
                .map(reviewModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reviews,
                linkTo(methodOn(ReviewController.class).getReviews()).withSelfRel()
        );
    }
}
