package com.intlfoodsource.IFSHardware.mapper;

import com.intlfoodsource.IFSHardware.model.Hardware;
import com.intlfoodsource.IFSHardware.request.HardwareRequest;
import com.intlfoodsource.IFSHardware.response.HardwareResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HardwareMapper {
    // model -> response
    HardwareResponse toHardwareResponse(Hardware hardware);

    // request -> model
    void toHardware(@MappingTarget Hardware hardware, HardwareRequest hardwareRequest);
}
