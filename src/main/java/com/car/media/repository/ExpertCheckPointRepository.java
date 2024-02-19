package com.car.media.repository;


import com.car.media.entity.ExpertCheckPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertCheckPointRepository extends JpaRepository<ExpertCheckPoint, Long> {
}
