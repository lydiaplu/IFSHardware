package com.intlfoodsource.IFSHardware.mapper;

import com.intlfoodsource.IFSHardware.model.Hardware;
import com.intlfoodsource.IFSHardware.model.HardwareCategory;
import com.intlfoodsource.IFSHardware.model.HardwareDocument;
import com.intlfoodsource.IFSHardware.model.HardwarePicture;
import com.intlfoodsource.IFSHardware.request.HardwareRequest;
import com.intlfoodsource.IFSHardware.response.HardwareCategoryResponse;
import com.intlfoodsource.IFSHardware.response.HardwareDocumentResponse;
import com.intlfoodsource.IFSHardware.response.HardwarePictureResponse;
import com.intlfoodsource.IFSHardware.response.HardwareResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface HardwareMapper {
    // model -> response
    HardwareResponse toHardwareResponse(Hardware hardware);

    HardwareCategoryResponse toHardwareCategoryResponse(HardwareCategory hardwareCategory);
    HardwarePictureResponse toHardwarePictureResponse(HardwarePicture hardwarePicture);
    HardwareDocumentResponse toHardwareDocumentResponse(HardwareDocument hardwareDocument);

    // request -> model
    void toHardware(@MappingTarget Hardware hardware, HardwareRequest hardwareRequest);
}
