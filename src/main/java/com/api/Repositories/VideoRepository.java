package com.api.Repositories;

import com.api.Entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:3000")
//@RepositoryRestResource(collectionResourceRel = "videos", path = "videos")
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByGameGameId(Long id);
}
