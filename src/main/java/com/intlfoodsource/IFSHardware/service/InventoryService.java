package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.exception.ResourceNotFoundException;
import com.intlfoodsource.IFSHardware.mapper.InventoryMapper;
import com.intlfoodsource.IFSHardware.model.Hardware;
import com.intlfoodsource.IFSHardware.model.HardwareLocation;
import com.intlfoodsource.IFSHardware.model.Inventory;
import com.intlfoodsource.IFSHardware.repository.InventoryRepository;
import com.intlfoodsource.IFSHardware.request.InventoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService{
    private final InventoryMapper inventoryMapper;
    private final InventoryRepository inventoryRepository;
    private final HardwareService hardwareService;
    private final HardwareLocationService hardwareLocationService;

    @Override
    public Inventory addInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        setInventoryModel(inventory, inventoryRequest);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Integer id, InventoryRequest inventoryRequest) {
        Inventory inventory = getInventoryById(id);
        setInventoryModel(inventory, inventoryRequest);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory getInventoryById(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id: " + id));
    }

    @Override
    public List<Inventory> getInventoryAll() {
        return inventoryRepository.findAll(Sort.by("id").ascending());
    }

    @Override
    public void deleteInventory(Integer id) {
        Inventory inventory = getInventoryById(id);
        inventoryRepository.delete(inventory);
    }

    @Override
    public List<Inventory> getInventoryByHardwareId(Integer hardwareId) {
        return inventoryRepository.findInventoryByHardwareId(hardwareId);
    }

    @Override
    public List<Inventory> getInventoryByHardwareName(String hardwareName) {
        return List.of();
    }

    @Override
    public List<Inventory> getInventoryByLocationId(Integer locationId) {
        return List.of();
    }

    @Override
    public List<Inventory> getInventoryByLocationName(String locationName) {
        return List.of();
    }

    private void setInventoryModel(Inventory inventory, InventoryRequest inventoryRequest) {
        inventoryMapper.toInventory(inventory, inventoryRequest);

        // Set Hardware foreign key
        Hardware hardware = hardwareService.getHardwareById(inventoryRequest.getHardwareId());
        inventory.setHardware(hardware);

        // Set Location foreign key
        HardwareLocation location = hardwareLocationService.getHardwareLocationById(inventoryRequest.getLocationId());
        inventory.setLocation(location);
    }
}
