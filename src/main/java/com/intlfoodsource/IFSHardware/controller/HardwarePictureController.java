package com.intlfoodsource.IFSHardware.controller;

import com.intlfoodsource.IFSHardware.mapper.HardwarePictureMapper;
import com.intlfoodsource.IFSHardware.model.HardwarePicture;
import com.intlfoodsource.IFSHardware.request.HardwarePictureRequest;
import com.intlfoodsource.IFSHardware.response.ApiResponse;
import com.intlfoodsource.IFSHardware.response.HardwarePictureResponse;
import com.intlfoodsource.IFSHardware.service.HardwarePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pictures")
public class HardwarePictureController {
    private final HardwarePictureService hardwarePictureService;
    private final HardwarePictureMapper hardwarePictureMapper;

    @PostMapping("/add/new-picture")
    public ResponseEntity<ApiResponse<HardwarePictureResponse>> addHardwarePicture(
            @RequestBody HardwarePictureRequest hardwarePictureRequest
    ) {
        HardwarePicture savedHardwarePicture = hardwarePictureService.addHardwarePicture(hardwarePictureRequest);
        HardwarePictureResponse hardwarePictureResponse = hardwarePictureMapper.toHardwarePictureResponse(savedHardwarePicture);
        return ResponseEntity.ok(ApiResponse.created(hardwarePictureResponse));
    }

    @PutMapping("/update/{pictureId}")
    public ResponseEntity<ApiResponse<HardwarePictureResponse>> updateHardwarePicture(
            @PathVariable Integer pictureId,
            @RequestBody HardwarePictureRequest hardwarePictureRequest
    ) {
        HardwarePicture savedHardwarePicture = hardwarePictureService.updateHardwarePicture(pictureId, hardwarePictureRequest);
        HardwarePictureResponse hardwarePictureResponse = hardwarePictureMapper.toHardwarePictureResponse(savedHardwarePicture);
        return ResponseEntity.ok(ApiResponse.success(hardwarePictureResponse));
    }

    @GetMapping("/picture/{pictureId}")
    public ResponseEntity<ApiResponse<HardwarePictureResponse>> getHardwarePictureById(
            @PathVariable Integer pictureId
    ) {
        HardwarePicture hardwarePicture = hardwarePictureService.getHardwarePictureById(pictureId);
        HardwarePictureResponse hardwarePictureResponse = hardwarePictureMapper.toHardwarePictureResponse(hardwarePicture);
        return ResponseEntity.ok(ApiResponse.success(hardwarePictureResponse));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<HardwarePictureResponse>>> getHardwarePictureAll() {
        List<HardwarePicture> hardwarePictures = hardwarePictureService.getHardwarePictureAll();
        List<HardwarePictureResponse> hardwarePictureResponses = hardwarePictures.stream()
                .map(hardwarePictureMapper::toHardwarePictureResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(hardwarePictureResponses));
    }

    @DeleteMapping("/delete/{pictureId}")
    public ResponseEntity<ApiResponse<String>> deleteHardwarePicture(
            @PathVariable Integer pictureId
    ) {
        hardwarePictureService.deleteHardwarePicture(pictureId);
        return ResponseEntity.ok(ApiResponse.success("Hardware Picture deleted successfully."));
    }

    @GetMapping("/picture/by-hardwareId/{hardwareId}")
    public ResponseEntity<ApiResponse<List<HardwarePictureResponse>>> getHardwarePicturesByHardwareId(
            @PathVariable Integer hardwareId
    ) {
        List<HardwarePicture> hardwarePictures = hardwarePictureService.getHardwarePictureByHardwareId(hardwareId);
        List<HardwarePictureResponse> hardwarePictureResponses = hardwarePictures.stream()
                .map(hardwarePictureMapper::toHardwarePictureResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(hardwarePictureResponses));
    }
}
