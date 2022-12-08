package com.example.demo.Asset;

import com.example.demo.Room.Room;
import com.example.demo.Room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository repo;

    public List<Asset> listAll(){
        return (List<Asset>) repo.findAll();
    }

    public List<Asset> listAllMore1K(){
        return (List<Asset>) repo.findAllAssetPriceMoreThan1K();
    }



}
