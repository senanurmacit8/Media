package com.car.media.repository;

import com.car.media.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<Expert,Long> {

    List<Expert> findAllByCarIdAndStatus(long carId , int isActive);
}
