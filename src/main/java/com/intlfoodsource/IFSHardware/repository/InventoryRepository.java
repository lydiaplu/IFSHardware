package com.intlfoodsource.IFSHardware.repository;

import com.intlfoodsource.IFSHardware.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Query("SELECT i FROM Inventory i WHERE i.hardware.id = :hardwareId")
    List<Inventory> findInventoryByHardwareId(Integer hardwareId);

    @Query("SELECT i FROM Inventory i WHERE i.hardware.name LIKE %:hardwareName%")
    List<Inventory> findInventoryByHardwareName(String hardwareName);

    @Query("SELECT i FROM Inventory i WHERE i.location.id = :locationId")
    List<Inventory> findInventoryByLocationId(Integer locationId);

    @Query("SELECT i FROM Inventory i WHERE i.location.name LIKE %:locationName%")
    List<Inventory> findInventoryByLocationName(String locationName);
}
