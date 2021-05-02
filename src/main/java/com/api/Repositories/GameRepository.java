package com.api.Repositories;

import com.api.Entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByVideosVideoId(Long id);
    Game findByImagesImageId(Long id);
    List<Game> findAllByFranchiseGameGameIdAndGameIdNot(Long id, Long id2);
    Game findByScoresScoreId(Long id);
}
