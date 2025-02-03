package com.intlfoodsource.IFSHardware.mapper;

import com.intlfoodsource.IFSHardware.model.HardwareLocation;
import com.intlfoodsource.IFSHardware.request.HardwareLocationRequest;
import com.intlfoodsource.IFSHardware.response.HardwareLocationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HardwareLocationMapper {
    // model -> response
    HardwareLocationResponse toHardwareLocationResponse(HardwareLocation hardwareLocation);

    // request -> model
    void toHardwareLocation(@MappingTarget HardwareLocation hardwareLocation, HardwareLocationRequest hardwareLocationRequest);
}
