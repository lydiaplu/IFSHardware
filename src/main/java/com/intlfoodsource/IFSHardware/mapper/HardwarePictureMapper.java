package com.intlfoodsource.IFSHardware.mapper;

import com.intlfoodsource.IFSHardware.model.HardwarePicture;
import com.intlfoodsource.IFSHardware.request.HardwarePictureRequest;
import com.intlfoodsource.IFSHardware.response.HardwarePictureResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HardwarePictureMapper {
    // model -> response
    HardwarePictureResponse toHardwarePictureResponse(HardwarePicture hardwarePicture);

    // request -> model
    void toHardwarePicture(@MappingTarget HardwarePicture hardwarePicture, HardwarePictureRequest hardwarePictureRequest);
}
