package com.api.Repositories;

import com.api.Entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByGameGameId(Long id);
    List<Image> findAllByImageTypeAndGameGameId(String type, Long id);
    Image findOneByImageTypeAndGameGameId(String type, Long id);
}
