package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.HouseManagerTypeVo;
import com.roomfindingsystem.entity.*;

import com.roomfindingsystem.repository.TypeHouseRepository;
import com.roomfindingsystem.repository.UserRepository;
import com.roomfindingsystem.service.AddressService;
import com.roomfindingsystem.service.HouseManagerService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class HouseManagerController {
    private HouseManagerService houseManagerService;

    public HouseManagerController(HouseManagerService houseManagerService){
        this.houseManagerService = houseManagerService;
    }
    @Autowired
    TypeHouseRepository typeHouseRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    UserRepository userRepository;



    @GetMapping("/house-manager")
    public String viewHomepage(final Model model, HttpSession httpSession){
        List<HouseManagerTypeVo> houseList = houseManagerService.findHouseManager();
        model.addAttribute("houses", houseList);
        //entries từ 0 đến 5 vào jquery.dataTables.min.js" tìm entries sửa display = 5
        return "admin/house-manager";
    }

    @GetMapping("/house-manager/delete/{houseid}")
    public String deleteHouse(@PathVariable Integer houseid,HttpSession httpSession){
        houseManagerService.deleteHouse(houseid);
        return "redirect:/admin/house-manager";
    }

    @GetMapping("/house-manager/detail/{houseid}")
    public String updateHouse(@ModelAttribute("house") HouseLandlordVo house,@RequestParam(name = "service", required = false,defaultValue = "0") List<Integer> service,MultipartFile[] images, Model model, HttpSession httpSession) throws IOException {
        if(house.getProvinceID()==0){
            Optional<AddressEntity> newAddress = addressService.findbyId(house.getAddress());
            AddressEntity address = new AddressEntity("a",house.getAddressDetail(),newAddress.get().getProvinceId(),newAddress.get().getDistrictId(),newAddress.get().getWardId());
            addressService.updateAddress(address,house.getAddress());
        }else{
            AddressEntity address = new AddressEntity("a",house.getAddressDetail(),house.getProvinceID(),house.getDistrictID(),house.getWardID());
            addressService.updateAddress(address,house.getAddress());
        }
        System.out.println(house.getHouseID());
        System.out.println(service);

        houseManagerService.updateHouse(house,house.getHouseID(),service);

        return "redirect:/admin/house-manager";
    }
    @PostMapping("/house-manager/update")
    public String updateHouse(@RequestParam(name = "houseID",required = false , defaultValue = "") String houseID,
                              @RequestParam(name = "houseName",required = false , defaultValue = "") String houseName,
                              @RequestParam(name = "addressDetail",required = false , defaultValue = "") String addressDetail,
                              @RequestParam(name = "description",required = false , defaultValue = "") String description,
                              @ModelAttribute("house") HouseManagerTypeVo houseManager,
                              @RequestParam(name = "province",required = false , defaultValue = "0") String provinceSelect,
                              @RequestParam(name = "district",required = false , defaultValue = "0") String districtSelect,
                              @RequestParam(name = "ward",required = false , defaultValue = "0") String wardSelect,
                              @RequestParam(name = "provinceID",required = false , defaultValue = "0") String provinceID,
                              @RequestParam(name = "districtID",required = false , defaultValue = "0") String districtID,
                              @RequestParam(name = "wardID",required = false , defaultValue = "0") String wardID,
                              @RequestParam(name = "addressid",required = false , defaultValue = "1") String addressid,
                              MultipartFile[] images, Model model, HttpSession httpSession) throws IOException {
        int province,district,ward ;
        Integer createdBy = 1;
        LocalDate lastDate = LocalDate.now();
        if(provinceSelect.equals("0")){
            province = Integer.parseInt(provinceID);
        }else{
            province = Integer.parseInt(provinceSelect);
        }

        if(districtSelect.equals("0")){
            district=Integer.parseInt(districtID);
        }else{
            district=Integer.parseInt(districtSelect);
        }

        if(wardSelect.equals("0")){
            ward=Integer.parseInt(wardID);
        }else{
            ward = Integer.parseInt(wardSelect);
        }
        AddressEntity address = new AddressEntity("a",addressDetail,province,district,ward);
        addressService.updateAddress(address,Integer.parseInt(addressid));

//        HousesEntity house = new HousesEntity(houseName,description.trim(),lastDate,createdBy,Integer.parseInt(addressid),houseManager.getTypeHouseID());
//        houseManagerService.updateHouse(house,Integer.parseInt(houseID));

        return "redirect:/admin/house-manager";
    }

    @GetMapping("/house-manager/add")
    public String addHouse(final Model model,HttpSession httpSession){
        List<UserEntity> listUser = new ArrayList<>();
        listUser = userRepository.findAll();
        List<TypeHouseEntity> listType = typeHouseRepository.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listType",listType);
        return "admin/house-manager-add";
    }

    @PostMapping("/house-manager/save")
    public String saveHouse(@RequestParam(name = "houseName",required = false , defaultValue = "") String houseName,
                            @RequestParam(name = "landlord",required = false , defaultValue = "1") String landlord,
                            @RequestParam(name = "addressDetail",required = false , defaultValue = "") String addressDetail,
                            @RequestParam(name = "description",required = false , defaultValue = "") String description,
                            @RequestParam(name = "houseType",required = false , defaultValue = "1") String houseType,
                            @RequestParam(name = "province",required = false , defaultValue = "1") String province,
                            @RequestParam(name = "district",required = false , defaultValue = "1") String district,
                            @RequestParam(name = "ward",required = false , defaultValue = "1") String ward,
                            MultipartFile[] images, Model model, HttpSession httpSession) throws IOException {
        Integer createdBy = 1;
        LocalDate createdDate = LocalDate.now();
        System.out.println(province);
        System.out.println(district);
        System.out.println(ward);
        AddressEntity address = new AddressEntity("a",addressDetail,Integer.parseInt(province),Integer.parseInt(district),Integer.parseInt(ward));
        int addressID = addressService.insertAddress(address);

        Integer typeHouse = Integer.parseInt(houseType);
        Integer landlordId = Integer.parseInt(landlord);
//        HousesEntity house = new HousesEntity(houseName,description.trim(),createdDate,createdBy,createdDate,createdBy,addressID,typeHouse,landlordId,1);
//        houseManagerService.insertHouse(house);
//        for (MultipartFile image : images) {
//
//            // Lưu trữ thông tin ảnh vào database
//            HouseImagesEntity imageModel = new HouseImagesEntity();
//            imageModel.setCreatedDate(createdDate);
//
//            imageModel.setImageLink(image.getContentType());
//            imageModel.setHouseId(houseManagerService.getLastHouse().getHouseId());
//
//            // Lưu trữ ảnh vào database
//            houseManagerService.inserImageHouse(imageModel);
//        }

        // Thêm danh sách ảnh vào model


        return "redirect:/admin/house-manager";
    }




}
