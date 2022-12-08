package com.example.demo.Room_Asset;

import com.example.demo.Room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Room_AssetService {

    @Autowired
    private Room_AssetRepository repo;

    public List<Room_Asset> listRoomAsset(){
        return (List<Room_Asset>) repo.findAll();
    }

    public List<Room_Asset> listRoomAssetByIdRoom(Integer idRoom){
        return (List<Room_Asset>) repo.getListAssetByRoomId(idRoom);
    }
}
