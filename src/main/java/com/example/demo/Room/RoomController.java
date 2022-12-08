package com.example.demo.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService service;

    @GetMapping("/rooms")
    public String showRoomList(Model model){
        List<Room> listRooms = service.listAll();
        model.addAttribute("listRooms",listRooms);
        return "rooms";
    }
}
