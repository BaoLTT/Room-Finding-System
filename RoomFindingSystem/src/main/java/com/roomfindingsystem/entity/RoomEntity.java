package com.roomfindingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room", schema = "room_finding_system", catalog = "")
public class RoomEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "roomid")
    private int roomId;
    @Basic
    @Column(name = "area")
    private Double area;
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;
    @Basic
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "houseid")
    private Integer houseId;
    @Basic
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;
    @Basic
    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;
    @Basic
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "room_name")
    private String roomName;
    @Basic
    @Column(name = "room_type")
    private Integer roomType;
    @Basic
    @Column(name = "floor")
    private Integer floor;
    @Basic
    @Column(name = "statusid")
    private Integer statusId;
    @Basic
    @Column(name = "status_update_date")
    private LocalDate statusUpdateDate;

    public int getRoomid() {
        return roomId;
    }

    public void setRoomid(int roomid) {
        this.roomId = roomid;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHouseid() {
        return houseId;
    }

    public void setHouseid(Integer houseid) {
        this.houseId = houseid;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }
    public Integer getFloor() {
        return this.floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getStatusid() {
        return statusId;
    }

    public void setStatusId(Integer statusid) {
        this.statusId = statusid;
    }

    public LocalDate getStatusUpdateDate() {
        return statusUpdateDate;
    }

    public void setStatusUpdateDate(LocalDate statusUpdateDate) {
        this.statusUpdateDate = statusUpdateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return roomId == that.roomId && Objects.equals(area, that.area) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdDate, that.createdDate) && Objects.equals(description, that.description) && Objects.equals(houseId, that.houseId) && Objects.equals(lastModifiedBy, that.lastModifiedBy) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(price, that.price) && Objects.equals(roomName, that.roomName) && Objects.equals(roomType, that.roomType) && Objects.equals(statusId, that.statusId) && Objects.equals(statusUpdateDate, that.statusUpdateDate);
    }

    public RoomEntity(int roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, area, createdBy, createdDate, description, houseId, lastModifiedBy, lastModifiedDate, price, roomName, roomType, statusId, statusUpdateDate);
    }
}
