package com.car.media.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "T_EXPERT")
public class Expert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column
    private Long carId;

    @Column
    private int status;

    @OneToMany
    private List<ExpertCheckPoint> expertCheckPointList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ExpertCheckPoint> getExpertCheckPointList() {
        return expertCheckPointList;
    }

    public void setExpertCheckPointList(List<ExpertCheckPoint> expertCheckPointList) {
        this.expertCheckPointList = expertCheckPointList;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
