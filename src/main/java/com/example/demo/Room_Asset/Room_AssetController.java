package com.example.demo.Room_Asset;

import com.example.demo.Room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class Room_AssetController {

    @Autowired
    private Room_AssetService service;

    @Autowired
    private Room_AssetRepository repo;




    @GetMapping("/asset/{idRoom}")
    public String showAssetListByIdRoom(Model model
            , @PathVariable("idRoom") Integer idRoom){
        List<Room_Asset> listRoomAssets = service.listRoomAssetByIdRoom(idRoom);

        model.addAttribute("listRoomAssets",listRoomAssets);
        return "assets";
    }

}
