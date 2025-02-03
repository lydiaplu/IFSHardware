package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.model.Hardware;
import com.intlfoodsource.IFSHardware.request.HardwareRequest;

import java.util.List;

public interface IHardwareService {
    Hardware addHardware(HardwareRequest hardwareRequest);

    Hardware updateHardware(Integer id, HardwareRequest hardwareRequest);

    Hardware getHardwareById(Integer id);

    List<Hardware> getHardwareAll();

    void deleteHardware(Integer id);
}
