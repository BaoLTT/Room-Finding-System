package com.roomfindingsystem.controller;//package com.example.roomfindingsystem.controller;
//
//import com.roomfindingsystem.controller.HouseController;
//import com.roomfindingsystem.dto.*;
//import com.roomfindingsystem.entity.FeedbackEntity;
//import com.roomfindingsystem.entity.ReportEntity;
//import com.roomfindingsystem.entity.UserEntity;
//import com.roomfindingsystem.service.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.ui.ModelMap;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//
//
//@ExtendWith(MockitoExtension.class)
//public class HouseDetailControllerTest {
//    @InjectMocks
//    private HouseController houseController;
//
//    @Mock
//    private HouseService houseService;
//
//    @Mock
//    private FeedbackService feedbackService;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private ReportService reportService;
//
//    @Mock
//    private RoomService roomService;
//
//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllHouse() {
//        // Arrange
//        Integer houseId = 1;
//        ModelMap model = new ModelMap();
//
//        // Mocking the services
//        List<HouseDto> houseDtos = Arrays.asList(new HouseDto()); // Mock your data accordingly
//        when(houseService.getHouseDetail(houseId)).thenReturn(houseDtos);
//
//        List<ServiceDto> serviceDtos = Arrays.asList(new ServiceDto()); // Mock your data accordingly
//        when(houseService.getServiceById(houseId)).thenReturn(serviceDtos);
//
//        List<HouseImageLink> imageLinks = Arrays.asList(new HouseImageLink()); // Mock your data accordingly
//        when(houseService.getImageById(houseId)).thenReturn(imageLinks);
//
//        List<FeedbackDto> feedbacks = Arrays.asList(new FeedbackDto()); // Mock your data accordingly
//        when(feedbackService.getFeedbackByHouseId(houseId)).thenReturn(feedbacks);
//
////        UserEntity user = new UserEntity(); // Mock your user data accordingly
////        Mockito.lenient().when(userService.findByEmail("baoltthe153367@fpt.edu.vn")).thenReturn(Optional.of(user));
////
////        List<FeedbackEntity> feedbackEntities = Arrays.asList(new FeedbackEntity()); // Mock your data accordingly
////        Mockito.lenient().when(feedbackService.getFeedbackEntityByUid(houseId, user.getUserId())).thenReturn(feedbackEntities);
////
////        ReportEntity reportEntity = new ReportEntity(); // Mock your report data accordingly
////        Mockito.lenient().when(reportService.getReportEntityByUid(houseId, user.getUserId())).thenReturn(Arrays.asList(reportEntity));
//
//        List<RoomHouseDetailDto> roomHouseDetailDtos = Arrays.asList(new RoomHouseDetailDto()); // Mock your data accordingly
//        when(roomService.viewRoomInHouse(houseId)).thenReturn(roomHouseDetailDtos);
//
//        // Act
//        String result = houseController.getAllHouse(houseId, model);
//
//        // Assert
//        assertEquals("housedetail", result);
//        // Add more assertions based on the behavior of your controller
//    }
//}
