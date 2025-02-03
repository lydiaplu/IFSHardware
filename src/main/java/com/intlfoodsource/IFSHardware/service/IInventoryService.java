package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.model.Inventory;
import com.intlfoodsource.IFSHardware.request.InventoryRequest;

import java.util.List;

public interface IInventoryService {
    Inventory addInventory(InventoryRequest inventoryRequest);

    Inventory updateInventory(Integer id, InventoryRequest inventoryRequest);

    Inventory getInventoryById(Integer id);

    List<Inventory> getInventoryAll();

    void deleteInventory(Integer id);

    List<Inventory> getInventoryByHardwareId(Integer hardwareId);

    List<Inventory> getInventoryByHardwareName(String hardwareName);

    List<Inventory> getInventoryByLocationId(Integer locationId);

    List<Inventory> getInventoryByLocationName(String locationName);
}
