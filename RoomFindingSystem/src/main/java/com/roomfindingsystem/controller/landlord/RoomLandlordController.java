package com.roomfindingsystem.controller.landlord;

import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.service.RoomService;
import com.roomfindingsystem.service.RoomTypeService;
import com.roomfindingsystem.service.ServiceDetailService;
import com.roomfindingsystem.service.ServiceRoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("landlord/room")
@Controller
public class RoomLandlordController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private ServiceRoomService serviceRoomService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private ServiceDetailService serviceDetailService;
    @GetMapping("/listRoom/{id}")
    public String getListRoomPage(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        List<RoomDto> roomDtos = roomService.getRoomsInHouse(id);
        model.addAttribute("houseId", id);
        model.addAttribute("rooms", roomDtos);
        model.addAttribute("request",request);
        return "landlord/list-room";
    }

    @GetMapping("/updateRoom/{id}")
    public String getFormUpdateRoom(@PathVariable("id") Integer id, Model model, HttpServletRequest request){
        RoomDto roomDto = roomService.findById(id);
        model.addAttribute("room", roomDto);
        model.addAttribute("types", roomTypeService.findAll());
        model.addAttribute("listService", serviceDetailService.getAllService());
        model.addAttribute("listChecked", roomDto.getServiceNames());
        model.addAttribute("request",request);
        return "landlord/edit-room";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "room") RoomDto roomDto, @RequestParam("file") MultipartFile[] files) throws IOException {
        List<ServiceDto> serviceDtos = new ArrayList<>();
        List<String> selects = roomDto.getServiceNames();
        if (selects!=null) {
            for (String serviceName : selects) {
                ServiceDto serviceDto = new ServiceDto();
                serviceDto.setServiceName(serviceName);
                serviceDto.setServiceId(serviceDetailService.findByName(serviceName).getServiceId());
                serviceDtos.add(serviceDto);
            }
        }
        roomDto.setServiceDtos(serviceDtos);
        roomService.update(roomDto, files);
        return "redirect:/landlord/room/updateRoom/" + roomDto.getRoomId();
    }

    @GetMapping("/deleteRoom/{houseId}/{id}")
    public String delete(@PathVariable("id") Integer id,@PathVariable("houseId") Integer houseId){
        roomService.deleteById(id);
        return "redirect:/landlord/room/listRoom/"+houseId;
    }
    @GetMapping("/insertRoom/{houseId}")
    public String insertRoomPage(@PathVariable("houseId") Integer id,Model model, HttpServletRequest request) {
        RoomDto roomDto = new RoomDto();
        model.addAttribute("room", roomDto);
        model.addAttribute("houseId", id);
        model.addAttribute("services", serviceDetailService.getAllService());
        model.addAttribute("types", roomTypeService.findAll());
        model.addAttribute("request",request);
        return "landlord/insert-room";
    }

    @PostMapping("/save/{houseId}")
    public String save(@PathVariable("houseId") Integer id,@ModelAttribute(name = "room") RoomDto roomDto, @RequestParam("file") MultipartFile[] files) throws IOException {

            List<ServiceDto> serviceDtos = new ArrayList<>();
            List<String> selects = roomDto.getServiceNames();
            if (selects!=null){
                for (String serviceName : selects) {
                    ServiceDto serviceDto = new ServiceDto();
                    serviceDto.setServiceName(serviceName);
                    serviceDto.setServiceId(serviceDetailService.findByName(serviceName).getServiceId());
                    serviceDtos.add(serviceDto);
                }
                roomDto.setServiceDtos(serviceDtos);
            }
            roomService.saveRoomLandlord(roomDto, files);


        return "redirect:/landlord/room/listRoom/"+ id;
    }

    @PostMapping("/importRooms/{houseId}")
    public String importRoom(@PathVariable("houseId") Integer id,@RequestParam("fileExcel") MultipartFile fileExcel) {
        roomService.importRooms(fileExcel);
        return "redirect:/landlord/room/listRoom"+ id;
    }

    @GetMapping("deleteImage/{roomId}/{imageId}")
    public String deleteImage(@PathVariable Integer roomId, @PathVariable Integer imageId) {
        roomService.deleteRoomImage(imageId);
        return "redirect:/landlord/room/updateRoom/" + roomId;
    }
}

