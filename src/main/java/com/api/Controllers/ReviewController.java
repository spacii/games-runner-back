package com.api.Controllers;

import com.api.Entities.Review;
import com.api.Entities.User;
import com.api.Services.ReviewService;
import com.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/reviews")
    public CollectionModel<EntityModel<Review>> getReviews(){
        return reviewService.getReviews();
    }

    @CrossOrigin
    @GetMapping("/reviews/{id}")
    public EntityModel<Review> getReviewById(@PathVariable Long id){
        return reviewService.getReviewById(id);
    }

    @CrossOrigin
    @PostMapping("/reviews")
    public EntityModel<Review> addReview(@RequestBody Review newReview, @RequestParam Long scoreId){
        return reviewService.addReview(newReview, scoreId);
    }

    @CrossOrigin
    @PutMapping("/reviews/{reviewId}")
    public EntityModel<Review> updateReview(@RequestBody Review newReview, @PathVariable Long reviewId){
        return reviewService.updateReview(newReview, reviewId);
    }

    @CrossOrigin
    @DeleteMapping("/reviews/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
    }

    @CrossOrigin
    @GetMapping("/reviews/{reviewId}/user")
    public EntityModel<User> getReviewUser(@PathVariable Long reviewId){
        return userService.getUserByReviewId(reviewId);
    }
}
