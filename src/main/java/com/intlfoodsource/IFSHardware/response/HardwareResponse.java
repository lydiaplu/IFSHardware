package com.intlfoodsource.IFSHardware.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HardwareResponse {
    private Integer id;
    private String name;
    private String specification;
    private String description;
    private HardwareCategoryResponse category;
    private List<HardwarePictureResponse> hardwarePictures;
    private List<HardwareDocumentResponse> hardwareDocuments;

    public HardwareResponse(Integer id, String name, String specification, String description,
                            HardwareCategoryResponse category, List<HardwarePictureResponse> hardwarePictures,
                            List<HardwareDocumentResponse> hardwareDocuments) {
        this.id = id;
        this.name = name;
        this.specification = specification != null ? specification : "";
        this.description = description != null ? description : "";
        this.category = category;
        this.hardwarePictures = hardwarePictures;
        this.hardwareDocuments = hardwareDocuments;
    }
}

