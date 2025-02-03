package com.intlfoodsource.IFSHardware.mapper;

import com.intlfoodsource.IFSHardware.model.HardwareCategory;
import com.intlfoodsource.IFSHardware.request.HardwareCategoryRequest;
import com.intlfoodsource.IFSHardware.response.HardwareCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HardwareCategoryMapper {
    // model -> response
    HardwareCategoryResponse toHardwareCategoryResponse(HardwareCategory hardwareCategory);

    // request -> model
    void toHardwareCategory(@MappingTarget HardwareCategory hardwareCategory, HardwareCategoryRequest hardwareCategoryRequest);
}
