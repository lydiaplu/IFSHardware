package com.intlfoodsource.IFSHardware.mapper;

import com.intlfoodsource.IFSHardware.model.HardwareDocument;
import com.intlfoodsource.IFSHardware.request.HardwareDocumentRequest;
import com.intlfoodsource.IFSHardware.response.HardwareDocumentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HardwareDocumentMapper {
    // model -> response
    HardwareDocumentResponse toHardwareDocumentResponse(HardwareDocument hardwareDocument);

    // request -> model
    void toHardwareDocument(@MappingTarget HardwareDocument hardwareDocument, HardwareDocumentRequest hardwareDocumentRequest);
}
