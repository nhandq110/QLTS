package com.example.demo.Room_Asset;

import com.example.demo.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface Room_AssetRepository extends JpaRepository<Room_Asset,Long> {

    @Query(
            value = "SELECT * FROM room_asset WHERE id_room= :id_room",
            nativeQuery = true)
    List<Room_Asset> getListAssetByRoomId(@Param("id_room") Integer id_room);






}
