package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.model.HardwarePicture;
import com.intlfoodsource.IFSHardware.request.HardwarePictureRequest;

import java.util.List;

public interface IHardwarePictureService {
    HardwarePicture addHardwarePicture(HardwarePictureRequest hardwarePictureRequest);

    HardwarePicture updateHardwarePicture(Integer id, HardwarePictureRequest hardwarePictureRequest);

    HardwarePicture getHardwarePictureById(Integer id);

    List<HardwarePicture> getHardwarePictureAll();

    void deleteHardwarePicture(Integer id);

    List<HardwarePicture> getHardwarePictureByHardwareId(Integer hardwareId);
}
