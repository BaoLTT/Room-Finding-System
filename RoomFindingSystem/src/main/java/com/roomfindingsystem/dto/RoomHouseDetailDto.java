package com.roomfindingsystem.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomHouseDetailDto {
    Integer roomId;

    Integer typeId;

    String typeName;

    List<String> roomList;

    Integer houseId;

    String houseName;

    Integer price;

}