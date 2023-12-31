package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.FavouriteDto;
import com.roomfindingsystem.dto.HouseImageLink;
import com.roomfindingsystem.dto.ReportListDto;
import com.roomfindingsystem.entity.FavouriteEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.FavouriteService;
import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class FavouriteController {
    @Autowired
    FavouriteService favouriteService;
    private UserService userService;

    @Autowired
    HouseService houseService;


    @RequestMapping(value = "favourite-list")
    public String getFavourite(@RequestParam(name = "id", required = false, defaultValue = "1") int houseId, Model model, HttpSession session, HttpServletRequest request) {
        model.addAttribute("request", request);
            session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");
            List<FavouriteDto> list = favouriteService.getListFavourite(user.getUserId());
            if (list.isEmpty()) {
                model.addAttribute("request", request);
                return "favourite-null";
            }
            System.out.println(list);

            model.addAttribute("houses", houseService.viewHouseInHomeInFavourite(user.getUserId()));
//       List<HouseImageLink> houseImageLinks= houseService.getImageById(houseId);
//        model.addAttribute("houseImageLinks",houseImageLinks);
//        model.addAttribute("listFavourite",list);

            return "favourite-list";
    }

    @RequestMapping(value = "remove-favourite-list")
    public String removeFavourite(@RequestParam("id") Integer id) {
        try {
            favouriteService.removeItemFavourite(id);
            return "redirect:/favourite-list";

        } catch (Exception exception) {
            exception.printStackTrace();
            return "404";
        }

    }


    @RequestMapping(value = "add-favourite-list")
    public String addToFavourite(@RequestParam("id") Integer id, Model model, HttpSession session, HttpServletRequest request) {
        try {
            session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");

            if (user!=null){
                if (!favouriteService.getAllByHouseId(user.getUserId(), id).isPresent()) {
                    FavouriteEntity favouriteEntity = new FavouriteEntity();
                    LocalDate now = LocalDate.now();
                    // get session id
                    favouriteEntity.setUserId(user.getUserId());
                    favouriteEntity.setCreatedDate(now);
                    favouriteEntity.setHouseId(id);
                    model.addAttribute("request",request);
                    favouriteService.addToFavourite(favouriteEntity);
                } else {
                    System.out.println("false");
                }
            }else {
                return "redirect:/login";
            }


            return "redirect:/houselist";


        } catch (Exception exception) {
            model.addAttribute("request",request);
            exception.printStackTrace();
            return "404";
        }

    }


}
