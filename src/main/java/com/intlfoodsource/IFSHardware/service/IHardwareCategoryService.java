package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.model.HardwareCategory;
import com.intlfoodsource.IFSHardware.request.HardwareCategoryRequest;

import java.util.List;

public interface IHardwareCategoryService {
    HardwareCategory addHardwareCategory(HardwareCategoryRequest hardwareCategoryRequest);

    HardwareCategory updateHardwareCategory(Integer id, HardwareCategoryRequest hardwareCategoryRequest);

    HardwareCategory getHardwareCategoryById(Integer id);

    List<HardwareCategory> getHardwareCategoryAll();

    void deleteHardwareCategory(Integer id);
}
