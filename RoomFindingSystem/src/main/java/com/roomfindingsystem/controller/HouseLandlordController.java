package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.entity.TypeHouseEntity;

import com.roomfindingsystem.service.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/manager")
public class HouseLandlordController {
    @Autowired
    private HouseLandlordService houseLandlordService;
    @Autowired
    HouseTypeService houseTypeService;
    @Autowired
    ServiceDetailService serviceDetailService;

    @Autowired
    AddressService addressService;

    @Autowired
    HouseManagerService houseManagerService;

    @GetMapping("")
        List<HouseLandlordVo> listHouse = new ArrayList<>();
        model.addAttribute("house",listHouse);
        return "managerHouse";
    }

    @GetMapping("/add")
    public String addHouse(Model model,HttpSession httpSession){
        HouseLandlordVo house = new HouseLandlordVo();

        List<TypeHouseEntity> listType = houseTypeService.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();
        model.addAttribute("house",house);
        model.addAttribute("listType",listType);
        model.addAttribute("listService",listService);
        return "managerAdd";
    }

    @GetMapping("/edit/{houseid}")
    public String detailHouse(@PathVariable Integer houseid,Model model, HttpSession httpSession){
        List<TypeHouseEntity> listType = houseTypeService.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();

        HouseLandlordVo  house = houseLandlordService.findHouseByID(houseid);
        List<String> listChecked = house.getService();
        System.out.println(listChecked);
                model.addAttribute("house",house);
        model.addAttribute("listType",listType);
        model.addAttribute("listChecked",listChecked);
        model.addAttribute("listService",listService);
        return "managerDetail";
    }

    @PostMapping("/save")
        AddressEntity address = new AddressEntity("a",house.getAddressDetail().trim(),house.getProvinceID(),house.getDistrictID(),house.getWardID());
        int addressID = addressService.insertAddress(address);
        return  "redirect:/manager";
    }

    @PostMapping("/update")
        if(house.getProvinceID()==0){
            Optional<AddressEntity> newAddress = addressService.findbyId(house.getAddress());
            AddressEntity address = new AddressEntity("a",house.getAddressDetail(),newAddress.get().getProvinceId(),newAddress.get().getDistrictId(),newAddress.get().getWardId());
            addressService.updateAddress(address,house.getAddress());
        }else{
            AddressEntity address = new AddressEntity("a",house.getAddressDetail(),house.getProvinceID(),house.getDistrictID(),house.getWardID());
            addressService.updateAddress(address,house.getAddress());
        }
        System.out.println(house.getHouseID());

        return  "redirect:/manager";
    }
}
