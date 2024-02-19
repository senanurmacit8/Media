package com.car.media.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "T_PHOTO")
public class PhotoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String photoURL;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private ExpertCheckPoint expertCheckPoint;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExpertCheckPoint getExpertCheckPoint() {
        return expertCheckPoint;
    }

    public void setExpertCheckPoint(ExpertCheckPoint expertCheckPoint) {
        this.expertCheckPoint = expertCheckPoint;
    }
}
