package com.example.demo.Asset;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Room.Room;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset,Long>{

    @Query(
            value = "SELECT * FROM asset WHERE price > 10000",
            nativeQuery = true)
    List<Asset> findAllAssetPriceMoreThan1K();
}
