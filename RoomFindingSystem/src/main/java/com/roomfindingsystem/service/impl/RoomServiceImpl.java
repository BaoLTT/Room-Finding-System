package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.*;
import com.roomfindingsystem.reponsitory.*;
import com.roomfindingsystem.service.RoomService;

import com.roomfindingsystem.dto.*;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final ModelMapper modelMapper;
    private final ServiceRoomRepository serviceRoomRepository;
    private final ServiceDetailRepository serviceDetailRepository;

    @Override
    public RoomEntity getRoomById(int roomId) {
        return roomRepository.getRoomById(roomId);

    }

    @Override
    public List<RoomImagesEntity> roomImageByRoomId(int roomId) {

        return roomRepository.getImageByRoomId(roomId);

    }

    @Override
    public List<ServiceDetailEntity> getServiceByRoomId(int roomId) {
		return roomRepository.getServiceByRoomId(roomId);
    }

    @Override
    public List<RoomDto> getAll() {
        List<RoomEntity> roomEntities = roomRepository.findAllRooms();

        List<RoomDto> roomDtos = roomEntities.stream().map(roomEntity -> {
            RoomDto roomDto = modelMapper.map(roomEntity, RoomDto.class);
            roomDto.setTypeName(roomTypeRepository.findById(roomEntity.getRoomType()).get().getTypeName());
            if (roomEntity.getStatusId() == 1) {
                roomDto.setStatus("ACTIVE");
            } else {
                roomDto.setStatus("INACTIVE");
            }
            List<ServiceDetailEntity> serviceDetailEntities = roomRepository.getServiceByRoomId(roomDto.getRoomId());

            StringBuilder servicesBuilder = new StringBuilder();

            for (ServiceDetailEntity serviceDetailEntity : serviceDetailEntities) {
                if (!servicesBuilder.isEmpty()) {
                    servicesBuilder.append(", ");
                }
                servicesBuilder.append(serviceDetailEntity.getServiceName());
            }
            roomDto.setServices(servicesBuilder.toString());

            return roomDto;
        }).toList();
        return roomDtos;
        }

    @Override
    public List<RoomHomeDto> viewRoomInHome() {
        List<Tuple> tuples = roomRepository.viewRoomInHome();
        List<RoomHomeDto> roomHomeDtos = new ArrayList<>();
        List<String> imageLinks ;

        for (Tuple tuple : tuples) {
            RoomHomeDto roomHomeDto = new RoomHomeDto();
            roomHomeDto.setRoomId(tuple.get("RoomID", Integer.class));
            roomHomeDto.setRoomName(tuple.get("Room_Name", String.class));
            roomHomeDto.setHouseName(tuple.get("House_Name", String.class));
            roomHomeDto.setRoomType(tuple.get("Type_Name", String.class));
            roomHomeDto.setAddressDetail(tuple.get("Address_Details", String.class));
            String imageLink = (tuple.get("Image_Link", String.class));
            if(imageLink == null)
            {roomHomeDto.setRoomImageLink(null);}
            else {imageLinks = Arrays.asList(imageLink.split(","));
                roomHomeDto.setRoomImageLink(imageLinks.get(0));}
            roomHomeDto.setProvince(tuple.get("province_name", String.class));
            roomHomeDto.setDistrict(tuple.get("district_name", String.class));
            roomHomeDto.setWard(tuple.get("ward_name", String.class));
            roomHomeDto.setArea(tuple.get("area", Double.class));
            roomHomeDto.setPrice(tuple.get("price",Integer.class));

            roomHomeDtos.add(roomHomeDto);
        }


        return roomHomeDtos;

    }

    @Override
    public RoomDto findById(Integer id) {
        Optional<RoomEntity> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            return null;
        }
        RoomEntity roomEntity = room.get();
        RoomDto roomDto = modelMapper.map(roomEntity, RoomDto.class);
        roomDto.setTypeName(roomTypeRepository.findById(roomEntity.getRoomType()).get().getTypeName());
        roomDto.setTypeId(roomEntity.getRoomType());
        if (roomEntity.getStatusId() == 1) {
            roomDto.setStatus("ACTIVE");
        } else {
            roomDto.setStatus("INACTIVE");
        }
        List<ServiceDetailEntity> serviceDetailEntities = roomRepository.getServiceByRoomId(roomDto.getRoomId());

        StringBuilder servicesBuilder = new StringBuilder();
        List<String> serviceNames = new ArrayList<>();

        for (ServiceDetailEntity serviceDetailEntity : serviceDetailEntities) {
            if (!servicesBuilder.isEmpty()) {
                servicesBuilder.append(", ");
            }
            servicesBuilder.append(serviceDetailEntity.getServiceName());
            serviceNames.add(serviceDetailEntity.getServiceName());
        }
        roomDto.setServices(servicesBuilder.toString());
        List<ServiceDto> serviceDtos = serviceDetailEntities.stream().map(serviceDetailEntity -> {
            return modelMapper.map(serviceDetailEntity, ServiceDto.class);
        }).toList();
        roomDto.setServiceDtos(serviceDtos);
        roomDto.setServiceNames(serviceNames);
        return roomDto;
    }

//    @Override
//    public void update(RoomDto roomDto) {
//        RoomEntity room = roomRepository.findById(roomDto.getRoomId()).get();
//
//        RoomEntity saveRoom = new RoomEntity();
//
//        saveRoom.setRoomId(room.getRoomId());
//        saveRoom.setArea(roomDto.getArea());
//        saveRoom.setCreatedDate(room.getCreatedDate());
//        saveRoom.setCreatedBy(room.getCreatedBy());
//        saveRoom.setDescription(roomDto.getDescription());
//        saveRoom.setHouseId(room.getHouseId());
//        saveRoom.setLastModifiedBy(room.getLastModifiedBy());
//        saveRoom.setLastModifiedDate(LocalDate.now());
//        saveRoom.setPrice(roomDto.getPrice());
//        saveRoom.setRoomName(roomDto.getRoomName());
//        saveRoom.setRoomType(room.getRoomType());
//        if (Objects.equals(roomDto.getStatus(), "ACTIVE")) {
//            saveRoom.setStatusId(1);
//        } else {
//            saveRoom.setStatusId(0);
//        }
//        List<ServiceRoomEntity> serviceRoomEntities = serviceRoomRepository.findAllByRoomId(roomDto.getRoomId());
//        for (ServiceRoomEntity serviceRoomEntity : serviceRoomEntities) {
//            serviceRoomRepository.deleteByRoomIdAndServiceId(roomDto.getRoomId(), serviceRoomEntity.getServiceId());
//        }
//        for (ServiceDto serviceDto : roomDto.getServiceDtos()) {
//            ServiceRoomEntity serviceRoomEntity = new ServiceRoomEntity();
//            serviceRoomEntity.setServiceId(serviceDto.getServiceId());
//            serviceRoomEntity.setRoomId(saveRoom.getRoomId());
//            serviceRoomRepository.save(serviceRoomEntity);
//        }
//        roomRepository.save(saveRoom);
//    }

    @Override
    public void deleteById(Integer id) {
        List<ServiceRoomEntity> serviceRoomEntities = serviceRoomRepository.findAllByRoomId(Math.toIntExact(id));
        for (ServiceRoomEntity serviceRoomEntity : serviceRoomEntities) {
            serviceRoomRepository.deleteByRoomIdAndServiceId(Math.toIntExact(id), serviceRoomEntity.getServiceId());
        }
        roomRepository.deleteById(id);
    }

//    @Override
//    public void save(RoomDto roomDto) {
//        RoomEntity saveRoom = new RoomEntity();
//        saveRoom.setArea(roomDto.getArea());
//        saveRoom.setCreatedDate(LocalDate.now());
//        saveRoom.setCreatedBy(1);
//        saveRoom.setDescription(roomDto.getDescription());
//        saveRoom.setHouseId(1);
//        saveRoom.setLastModifiedBy(1);
//        saveRoom.setLastModifiedDate(LocalDate.now());
//        saveRoom.setPrice(roomDto.getPrice());
//        saveRoom.setRoomName(roomDto.getRoomName());
//        saveRoom.setRoomType(roomDto.getTypeId());
//        if (Objects.equals(roomDto.getStatus(), "ACTIVE")) {
//            saveRoom.setStatusId(1);
//        } else {
//            saveRoom.setStatusId(0);
//        }
//
//        roomRepository.save(saveRoom);
//
//        for (ServiceDto serviceDto : roomDto.getServiceDtos()) {
//            ServiceRoomEntity serviceRoomEntity = new ServiceRoomEntity();
//            serviceRoomEntity.setServiceId(serviceDto.getServiceId());
//            serviceRoomEntity.setRoomId(saveRoom.getRoomId());
//            serviceRoomRepository.save(serviceRoomEntity);
//        }
//    }

    @Override
    public int countRoom() {
        return roomRepository.countRoom();
    }


    @Override
    public List<RoomHouseDetailDto> viewRoomInHouse(int houseId) {
        List<Tuple> tuples = roomRepository.viewRoomInHouseDetail(houseId);
        List<RoomHouseDetailDto> roomDtos = new ArrayList<>();
        List<String> roomList ;
        Set<String> uniquePairs = new HashSet<>();

        for (Tuple tuple : tuples) {
//            int houseId = tuple.get("HouseID", Integer.class);
            int typeId = tuple.get("TypeID", Integer.class);
            String pair = houseId + "-" + typeId;

            // Kiểm tra xem cặp (HouseID, TypeID) đã xuất hiện chưa
            if (!uniquePairs.contains(pair)) {
                RoomHouseDetailDto roomDto = new RoomHouseDetailDto();
                roomDto.setRoomId(tuple.get("RoomID", Integer.class));
                roomDto.setTypeId(typeId);
                roomDto.setTypeName(tuple.get("type_name", String.class));
                roomDto.setHouseId(houseId);
                roomDto.setHouseName(tuple.get("house_name", String.class));
                roomDto.setPrice(tuple.get("price", Integer.class));

                String roomName = tuple.get("room_list", String.class);
                if (roomName == null) {
                    roomDto.setRoomList(null);
                } else {
                    roomList = Arrays.asList(roomName.split(","));
                    roomDto.setRoomList(roomList);
                }

                roomDtos.add(roomDto);

                // Đánh dấu cặp (HouseID, TypeID) đã xuất hiện
                uniquePairs.add(pair);
            }
        }

        return roomDtos;
    }

    @Override
    public List<RoomDtoN> findRoom1(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize) {
        List<Tuple> tuples = roomRepository.getRoomList(min, max, roomName, type, pageIndex, pageSize);
        List<RoomDtoN> roomDtos = new ArrayList<>();
        List<String> imageLinks ;

        for (Tuple tuple : tuples) {
            RoomDtoN roomDto = new RoomDtoN();
            roomDto.setRoomId(tuple.get("roomid", Integer.class));
            roomDto.setRoomName(tuple.get("room_name", String.class));
            roomDto.setHouseName(tuple.get("house_name", String.class));
            roomDto.setPrice(tuple.get("price", Integer.class));
            roomDto.setRoomType(tuple.get("type_name", String.class));
            String imageLink = (tuple.get("images", String.class));
            if(imageLink == null)
            {roomDto.setRoomImages(null);}
            else {imageLinks = Arrays.asList(imageLink.split(","));
                roomDto.setRoomImages(imageLinks);}


            roomDtos.add(roomDto);
        }
        return roomDtos;
    }


}
