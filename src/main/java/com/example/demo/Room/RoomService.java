package com.example.demo.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repo;

    public List<Room> listAll(){
        return (List<Room>) repo.findAll();
    }
}
