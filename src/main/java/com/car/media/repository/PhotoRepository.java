package com.car.media.repository;


import com.car.media.entity.ExpertCheckPoint;
import com.car.media.entity.PhotoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoInfo,Long> {

    List<PhotoInfo> findAllByPhotoURL(String photoURL);

    List<PhotoInfo> findAllByExpertCheckPointId(Long expertCheckPointId);

}
