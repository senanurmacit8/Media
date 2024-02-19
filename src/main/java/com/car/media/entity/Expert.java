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

    @OneToMany(fetch = FetchType.EAGER)
    private List<ExpertCheckPoint> expertCheckPointList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public List<ExpertCheckPoint> getExpertCheckPointList() {
        return expertCheckPointList;
    }

    public void setExpertCheckPointList(List<ExpertCheckPoint> expertCheckPointList) {
        this.expertCheckPointList = expertCheckPointList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
