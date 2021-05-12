package com.api.Controllers;

import com.api.Entities.Review;
import com.api.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

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
    @PostMapping("/reviews/{gameid}/{userid}")
    public EntityModel<Review> addReview(@RequestBody Review newReview, @PathVariable Long gameid, @PathVariable Long userid){
        return reviewService.addReview(newReview, gameid, userid);
    }

    @CrossOrigin
    @PutMapping("/reviews/{id}")
    public EntityModel<Review> updateReview(@RequestBody Review newReview, @PathVariable Long id){
        return reviewService.updateReview(newReview, id);
    }

    @CrossOrigin
    @DeleteMapping("/reviews/{id}")
    public void deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
    }
}
