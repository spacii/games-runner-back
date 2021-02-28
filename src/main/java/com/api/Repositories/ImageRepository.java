package com.api.Repositories;

import com.api.Entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByGameGameId(Long id);
    List<Image> findAllByImageTypeAndGameGameId(String type, Long id);
    Image findOneByImageTypeAndGameGameId(String type, Long id);
}
