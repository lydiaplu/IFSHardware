package com.intlfoodsource.IFSHardware.controller;

import com.intlfoodsource.IFSHardware.mapper.HardwareMapper;
import com.intlfoodsource.IFSHardware.model.Hardware;
import com.intlfoodsource.IFSHardware.request.HardwareRequest;
import com.intlfoodsource.IFSHardware.response.ApiResponse;
import com.intlfoodsource.IFSHardware.response.HardwareResponse;
import com.intlfoodsource.IFSHardware.service.HardwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hardwares")
public class HardwareController {
    private final HardwareService hardwareService;
    private final HardwareMapper hardwareMapper;

    @PostMapping("/add/new-hardware")
    public ResponseEntity<ApiResponse<HardwareResponse>> addHardware(
            @RequestBody HardwareRequest hardwareRequest
    ) {
        Hardware savedHardware = hardwareService.addHardware(hardwareRequest);
        HardwareResponse hardwareResponse = hardwareMapper.toHardwareResponse(savedHardware);
        return ResponseEntity.ok(ApiResponse.created(hardwareResponse));
    }

    @PutMapping("/update/{hardwareId}")
    public ResponseEntity<ApiResponse<HardwareResponse>> updateHardware(
            @PathVariable Integer hardwareId,
            @RequestBody HardwareRequest hardwareRequest
    ) {
        Hardware savedHardware = hardwareService.updateHardware(hardwareId, hardwareRequest);
        HardwareResponse hardwareResponse = hardwareMapper.toHardwareResponse(savedHardware);
        return ResponseEntity.ok(ApiResponse.success(hardwareResponse));
    }

    @GetMapping("/hardware/{hardwareId}")
    public ResponseEntity<ApiResponse<HardwareResponse>> getHardwareById(
            @PathVariable Integer hardwareId
    ) {
        Hardware hardware = hardwareService.getHardwareById(hardwareId);
        HardwareResponse hardwareResponse = hardwareMapper.toHardwareResponse(hardware);
        return ResponseEntity.ok(ApiResponse.success(hardwareResponse));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<HardwareResponse>>> getHardwareAll(){
         List<Hardware> hardwares = hardwareService.getHardwareAll();
         List<HardwareResponse> hardwareResponses = hardwares.stream()
                 .map(hardwareMapper::toHardwareResponse)
                 .toList();
         return ResponseEntity.ok(ApiResponse.success(hardwareResponses));

    }

    @DeleteMapping("/delete/{hardwareId}")
    public ResponseEntity<ApiResponse<String>> deleteHardware(
            @PathVariable Integer hardwareId
    ) {
        hardwareService.deleteHardware(hardwareId);
        return ResponseEntity.ok(ApiResponse.success("Hardware deleted successfully."));
    }
}
