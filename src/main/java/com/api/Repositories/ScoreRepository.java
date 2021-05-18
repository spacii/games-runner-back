package com.api.Repositories;

import com.api.Entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByGameGameId(Long id);
    List<Score> findAllByUserUserId(Long id);
    //Optional<Score> findByGameGameIdAndUserUserId(Long gameId, Long userId);
}
