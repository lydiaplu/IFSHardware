package com.intlfoodsource.IFSHardware.controller;

import com.intlfoodsource.IFSHardware.mapper.InventoryMapper;
import com.intlfoodsource.IFSHardware.model.Inventory;
import com.intlfoodsource.IFSHardware.request.InventoryRequest;
import com.intlfoodsource.IFSHardware.response.ApiResponse;
import com.intlfoodsource.IFSHardware.response.InventoryResponse;
import com.intlfoodsource.IFSHardware.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventories")
public class InventoryController {
    private final InventoryService inventoryService;
    private final InventoryMapper inventoryMapper;

    @PostMapping("/add/new-inventory")
    public ResponseEntity<ApiResponse<InventoryResponse>> addInventory(
            @RequestBody InventoryRequest inventoryRequest
    ) {
        Inventory savedInventory = inventoryService.addInventory(inventoryRequest);
        InventoryResponse inventoryResponse = inventoryMapper.toInventoryResponse(savedInventory);
        return ResponseEntity.ok(ApiResponse.created(inventoryResponse));
    }

    @PutMapping("/update/{inventoryId}")
    public ResponseEntity<ApiResponse<InventoryResponse>> updateInventory(
            @PathVariable Integer inventoryId,
            @RequestBody InventoryRequest inventoryRequest
    ) {
        Inventory updatedInventory = inventoryService.updateInventory(inventoryId, inventoryRequest);
        InventoryResponse inventoryResponse = inventoryMapper.toInventoryResponse(updatedInventory);
        return ResponseEntity.ok(ApiResponse.success(inventoryResponse));
    }

    @GetMapping("/inventory/{inventoryId}")
    public ResponseEntity<ApiResponse<InventoryResponse>> getInventoryById(
            @PathVariable Integer inventoryId
    ) {
        Inventory inventory = inventoryService.getInventoryById(inventoryId);
        InventoryResponse inventoryResponse = inventoryMapper.toInventoryResponse(inventory);
        return ResponseEntity.ok(ApiResponse.success(inventoryResponse));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> getInventoryAll() {
        List<Inventory> inventories = inventoryService.getInventoryAll();
        List<InventoryResponse> inventoryResponses = inventories.stream()
                .map(inventoryMapper::toInventoryResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(inventoryResponses));
    }

    @DeleteMapping("/delete/{inventoryId}")
    public ResponseEntity<ApiResponse<String>> deleteInventory(
            @PathVariable Integer inventoryId
    ) {
        inventoryService.deleteInventory(inventoryId);
        return ResponseEntity.ok(ApiResponse.success("Inventory deleted successfully."));
    }

    @GetMapping("/inventory/by-hardwareId/{hardwareId}")
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> getInventoryByHardwareId(
            @PathVariable Integer hardwareId
    ) {
        List<Inventory> inventories = inventoryService.getInventoryByHardwareId(hardwareId);
        List<InventoryResponse> inventoryResponses = inventories.stream()
                .map(inventoryMapper::toInventoryResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(inventoryResponses));
    }

    @GetMapping("/inventory/by-hardwareName/{hardwareName}")
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> getInventoryByHardwareName(
            @PathVariable String hardwareName
    ) {
        List<Inventory> inventories = inventoryService.getInventoryByHardwareName(hardwareName);
        List<InventoryResponse> inventoryResponses = inventories.stream()
                .map(inventoryMapper::toInventoryResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(inventoryResponses));
    }

    @GetMapping("/inventory/by-locationId/{locationId}")
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> getInventoryByLocationId(
            @PathVariable Integer locationId
    ) {
        List<Inventory> inventories = inventoryService.getInventoryByLocationId(locationId);
        List<InventoryResponse> inventoryResponses = inventories.stream()
                .map(inventoryMapper::toInventoryResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(inventoryResponses));
    }

    @GetMapping("/inventory/by-locationName/{locationName}")
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> getInventoryByLocationName(
            @PathVariable String locationName
    ) {
        List<Inventory> inventories = inventoryService.getInventoryByLocationName(locationName);
        List<InventoryResponse> inventoryResponses = inventories.stream()
                .map(inventoryMapper::toInventoryResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(inventoryResponses));
    }
}
