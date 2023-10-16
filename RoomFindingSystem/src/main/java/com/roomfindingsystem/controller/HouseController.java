package com.roomfindingsystem.controller;

import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.vo.HouseDto;
import com.roomfindingsystem.vo.HouseImageLink;
import com.roomfindingsystem.vo.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HouseController {
    @Autowired
    HouseService houseService;

    @RequestMapping(value = "houseDetail")
    public String index(Model model) {
//        model.addAttribute("User", new UserEntity());
        return "templates";
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String getAllHouse(@RequestParam("id") Integer houseId, ModelMap model) {
        List<HouseDto> houseDto = houseService.getHouseDetail(houseId);
        System.out.printf(houseDto.toString());


        model.addAttribute("HousesEntity", houseDto);

        List<ServiceDto> listService= houseService.getServiceById(houseId);
        model.addAttribute("HouseService", listService);
//        houseDto.ifPresent(user -> model.addAttribute("HousesEntity", user));
        // get Image
        List<HouseImageLink> listsImage = houseService.getImageById(houseId);
        System.out.println(listsImage.toString());
        model.addAttribute("HousesImages", listsImage);

        return "detail";
    }
}