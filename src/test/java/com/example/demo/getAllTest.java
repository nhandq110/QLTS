package com.example.demo;

import com.example.demo.Asset.Asset;
import com.example.demo.Asset.AssetRepository;
import com.example.demo.Room.Room;
import com.example.demo.Room.RoomRepository;
import com.example.demo.Room_Asset.Room_Asset;
import com.example.demo.Room_Asset.Room_AssetRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class getAllTest {

    @Autowired
    private Room_AssetRepository repo;

    @Test
    public void testAll(){
        Iterable<Room_Asset> rooms = repo.getListAssetByRoomId(1);

        Assertions.assertThat(rooms).hasSizeGreaterThan(0);
        for(Room_Asset r : rooms){
            System.out.println(r);
        }
    }
}
