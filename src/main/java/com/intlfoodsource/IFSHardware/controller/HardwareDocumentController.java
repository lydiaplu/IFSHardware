package com.intlfoodsource.IFSHardware.controller;

import com.intlfoodsource.IFSHardware.mapper.HardwareDocumentMapper;
import com.intlfoodsource.IFSHardware.model.HardwareDocument;
import com.intlfoodsource.IFSHardware.request.HardwareDocumentRequest;
import com.intlfoodsource.IFSHardware.response.ApiResponse;
import com.intlfoodsource.IFSHardware.response.HardwareDocumentResponse;
import com.intlfoodsource.IFSHardware.service.HardwareDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documents")
public class HardwareDocumentController {
    private final HardwareDocumentService hardwareDocumentService;
    private final HardwareDocumentMapper hardwareDocumentMapper;

    @PostMapping("/add/new-document")
    public ResponseEntity<ApiResponse<HardwareDocumentResponse>> addHardwareDocument(
            @RequestBody HardwareDocumentRequest hardwareDocumentRequest
    ) {
        HardwareDocument savedHardwareDocument = hardwareDocumentService.addHardwareDocument(hardwareDocumentRequest);
        HardwareDocumentResponse hardwareDocumentResponse = hardwareDocumentMapper.toHardwareDocumentResponse(savedHardwareDocument);
        return ResponseEntity.ok(ApiResponse.created(hardwareDocumentResponse));
    }

    @PutMapping("/update/{documentId}")
    public ResponseEntity<ApiResponse<HardwareDocumentResponse>> updateHardwareDocument(
            @PathVariable Integer documentId,
            @RequestBody HardwareDocumentRequest hardwareDocumentRequest
    ) {
        HardwareDocument savedHardwareDocument = hardwareDocumentService.updateHardwareDocument(documentId, hardwareDocumentRequest);
        HardwareDocumentResponse hardwareDocumentResponse = hardwareDocumentMapper.toHardwareDocumentResponse(savedHardwareDocument);
        return ResponseEntity.ok(ApiResponse.success(hardwareDocumentResponse));
    }

    @GetMapping("/document/{documentId}")
    public ResponseEntity<ApiResponse<HardwareDocumentResponse>> getHardwareDocumentById(
            @PathVariable Integer documentId
    ) {
        HardwareDocument hardwareDocument = hardwareDocumentService.getHardwareDocumentById(documentId);
        HardwareDocumentResponse hardwareDocumentResponse = hardwareDocumentMapper.toHardwareDocumentResponse(hardwareDocument);
        return ResponseEntity.ok(ApiResponse.success(hardwareDocumentResponse));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<HardwareDocumentResponse>>> getHardwareDocumentAll() {
        List<HardwareDocument> hardwareDocuments = hardwareDocumentService.getHardwareDocumentAll();
        List<HardwareDocumentResponse> hardwareDocumentResponses = hardwareDocuments.stream()
                .map(hardwareDocumentMapper::toHardwareDocumentResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(hardwareDocumentResponses));
    }

    @DeleteMapping("/delete/{documentId}")
    public ResponseEntity<ApiResponse<String>> deleteHardwareDocument(
            @PathVariable Integer documentId
    ) {
        hardwareDocumentService.deleteHardwareDocument(documentId);
        return ResponseEntity.ok(ApiResponse.success("Hardware Document deleted successfully."));
    }

    @GetMapping("/document/by-hardwareId/{hardwareId}")
    public ResponseEntity<ApiResponse<List<HardwareDocumentResponse>>> getHardwareDocumentsByHardwareId(
            @PathVariable Integer hardwareId
    ) {
        List<HardwareDocument> hardwareDocuments = hardwareDocumentService.getHardwareDocumentByHardwareId(hardwareId);
        List<HardwareDocumentResponse> hardwareDocumentResponses = hardwareDocuments.stream()
                .map(hardwareDocumentMapper::toHardwareDocumentResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(hardwareDocumentResponses));
    }
}
