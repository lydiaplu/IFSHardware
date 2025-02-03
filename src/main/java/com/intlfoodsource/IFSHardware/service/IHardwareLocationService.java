package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.model.HardwareLocation;
import com.intlfoodsource.IFSHardware.request.HardwareLocationRequest;

import java.util.List;

public interface IHardwareLocationService {
    HardwareLocation addHardwareLocation(HardwareLocationRequest hardwareLocationRequest);

    HardwareLocation updateHardwareLocation(Integer id, HardwareLocationRequest hardwareLocationRequest);

    HardwareLocation getHardwareLocationById(Integer id);

    List<HardwareLocation> getHardwareLocationAll();

    void deleteHardwareLocation(Integer id);
}
