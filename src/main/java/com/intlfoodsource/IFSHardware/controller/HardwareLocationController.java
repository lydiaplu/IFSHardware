package com.intlfoodsource.IFSHardware.controller;

import com.intlfoodsource.IFSHardware.mapper.HardwareLocationMapper;
import com.intlfoodsource.IFSHardware.model.HardwareLocation;
import com.intlfoodsource.IFSHardware.request.HardwareLocationRequest;
import com.intlfoodsource.IFSHardware.response.ApiResponse;
import com.intlfoodsource.IFSHardware.response.HardwareLocationResponse;
import com.intlfoodsource.IFSHardware.service.HardwareLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class HardwareLocationController {
    private final HardwareLocationService hardwareLocationService;
    private final HardwareLocationMapper hardwareLocationMapper;

    @PostMapping("/add/new-location")
    public ResponseEntity<ApiResponse<HardwareLocationResponse>> addHardwareLocation(
            @RequestBody HardwareLocationRequest hardwareLocationRequest
    ) {
        HardwareLocation savedHardwareLocation = hardwareLocationService.addHardwareLocation(hardwareLocationRequest);
        HardwareLocationResponse hardwareLocationResponse = hardwareLocationMapper.toHardwareLocationResponse(savedHardwareLocation);
        return ResponseEntity.ok(ApiResponse.created(hardwareLocationResponse));
    }

    @PutMapping("/update/{locationId}")
    public ResponseEntity<ApiResponse<HardwareLocationResponse>> updateHardwareLocation(
            @PathVariable Integer locationId,
            @RequestBody HardwareLocationRequest hardwareLocationRequest
    ) {
        HardwareLocation updatedHardwareLocation = hardwareLocationService.updateHardwareLocation(locationId, hardwareLocationRequest);
        HardwareLocationResponse hardwareLocationResponse = hardwareLocationMapper.toHardwareLocationResponse(updatedHardwareLocation);
        return ResponseEntity.ok(ApiResponse.success(hardwareLocationResponse));
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<ApiResponse<HardwareLocationResponse>> getHardwareLocationById(
            @PathVariable Integer locationId
    ) {
        HardwareLocation hardwareLocation = hardwareLocationService.getHardwareLocationById(locationId);
        HardwareLocationResponse hardwareLocationResponse = hardwareLocationMapper.toHardwareLocationResponse(hardwareLocation);
        return ResponseEntity.ok(ApiResponse.success(hardwareLocationResponse));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<HardwareLocationResponse>>> getHardwareLocationAll() {
        List<HardwareLocation> hardwareLocations = hardwareLocationService.getHardwareLocationAll();
        List<HardwareLocationResponse> hardwareLocationResponses = hardwareLocations.stream()
                .map(hardwareLocationMapper::toHardwareLocationResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(hardwareLocationResponses));
    }

    @DeleteMapping("/delete/{locationId}")
    public ResponseEntity<ApiResponse<String>> deleteHardwareLocation(
            @PathVariable Integer locationId
    ) {
        hardwareLocationService.deleteHardwareLocation(locationId);
        return ResponseEntity.ok(ApiResponse.success("Hardware Location deleted successfully."));
    }
}
