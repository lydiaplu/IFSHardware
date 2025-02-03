package com.intlfoodsource.IFSHardware.controller;

import com.intlfoodsource.IFSHardware.mapper.HardwareCategoryMapper;
import com.intlfoodsource.IFSHardware.model.HardwareCategory;
import com.intlfoodsource.IFSHardware.request.HardwareCategoryRequest;
import com.intlfoodsource.IFSHardware.response.ApiResponse;
import com.intlfoodsource.IFSHardware.response.HardwareCategoryResponse;
import com.intlfoodsource.IFSHardware.service.HardwareCategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class HardwareCategoryController {
    private final HardwareCategoryService hardwareCategoryService;
    private final HardwareCategoryMapper hardwareCategoryMapper;

    @PostMapping("/add/new-category")
    public ResponseEntity<ApiResponse<HardwareCategoryResponse>> addHardwareCategory(
            @RequestBody HardwareCategoryRequest hardwareCategoryRequest
    ) {
        HardwareCategory savedHardwareCategory = hardwareCategoryService.addHardwareCategory(hardwareCategoryRequest);
        HardwareCategoryResponse hardwareCategoryResponse = hardwareCategoryMapper.toHardwareCategoryResponse(savedHardwareCategory);
        return ResponseEntity.ok(ApiResponse.created(hardwareCategoryResponse));
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse<HardwareCategoryResponse>> updateHardwareCategory(
            @PathVariable Integer categoryId,
            @RequestBody HardwareCategoryRequest hardwareCategoryRequest
    ) {
        HardwareCategory savedHardwareCategory = hardwareCategoryService.addHardwareCategory(hardwareCategoryRequest);
        HardwareCategoryResponse hardwareCategoryResponse = hardwareCategoryMapper.toHardwareCategoryResponse(savedHardwareCategory);
        return ResponseEntity.ok(ApiResponse.success(hardwareCategoryResponse));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<HardwareCategoryResponse>> getHardwareCategoryById(
            @PathVariable Integer categoryId
    ){
        HardwareCategory hardwareCategory = hardwareCategoryService.getHardwareCategoryById(categoryId);
        HardwareCategoryResponse hardwareCategoryResponse = hardwareCategoryMapper.toHardwareCategoryResponse(hardwareCategory);
        return ResponseEntity.ok(ApiResponse.success(hardwareCategoryResponse));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<HardwareCategoryResponse>>> getHardwareCategoryAll() {
        List<HardwareCategory> hardwareCategories = hardwareCategoryService.getHardwareCategoryAll();
        List<HardwareCategoryResponse> hardwareCategoryResponses = hardwareCategories.stream()
                .map(hardwareCategoryMapper::toHardwareCategoryResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(hardwareCategoryResponses));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse<String>> deleteHardwareCategory(
            @PathVariable Integer categoryId
    ) {
        hardwareCategoryService.deleteHardwareCategory(categoryId);
        return ResponseEntity.ok(ApiResponse.success("Hardware Category deleted successfully."));
    }
}
