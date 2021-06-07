package com.api.Repositories;

import com.api.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByScoresScoreId(Long id);
    User findByScoresReviewReviewId(Long reviewId);

    User findByUserEmail(String userEmail);
    User findByUserLogin(String userLogin);
}
