package com.roomfindingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Table(name = "house_images", schema = "room_finding_system", catalog = "")
public class HouseImagesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ImageID")
    private int imageId;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "Image_Link")
    private String imageLink;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Basic
    @Column(name = "Created_Date")
    private LocalDate createdDate;

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "HouseID")
    private int houseId;

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseImagesEntity that = (HouseImagesEntity) o;
        return imageId == that.imageId && houseId == that.houseId && Objects.equals(imageLink, that.imageLink) && Objects.equals(createdDate, that.createdDate);
    }

    public HouseImagesEntity(int imageId, String imageLink) {
        this.imageId = imageId;
        this.imageLink = imageLink;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, imageLink, createdDate, houseId);
    }
}
