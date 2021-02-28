package com.api.Repositories;

import com.api.Entities.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
    Franchise findByGameGameId(Long id);
}
