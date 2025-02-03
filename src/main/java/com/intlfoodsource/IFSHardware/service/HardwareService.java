package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.mapper.HardwareMapper;
import com.intlfoodsource.IFSHardware.model.Hardware;
import com.intlfoodsource.IFSHardware.model.HardwareCategory;
import com.intlfoodsource.IFSHardware.repository.HardwareRepository;
import com.intlfoodsource.IFSHardware.request.HardwareRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HardwareService implements IHardwareService{
    private final HardwareMapper hardwareMapper;
    private final HardwareRepository hardwareRepository;
    private final HardwareCategoryService hardwareCategoryService;

    @Override
    public Hardware addHardware(HardwareRequest hardwareRequest) {
        Hardware hardware = new Hardware();
        setHardwareModel(hardware, hardwareRequest);
        return hardwareRepository.save(hardware);
    }

    @Override
    public Hardware updateHardware(Integer id, HardwareRequest hardwareRequest) {
        return null;
    }

    @Override
    public Hardware getHardwareById(Integer id) {
        return null;
    }

    @Override
    public List<Hardware> getHardwareAll() {
        return List.of();
    }

    @Override
    public void deleteHardware(Integer id) {

    }

    private void setHardwareModel(Hardware hardware, HardwareRequest hardwareRequest) {
        hardwareMapper.toHardware(hardware, hardwareRequest);

        HardwareCategory hardwareCategory = hardwareCategoryService.getHardwareCategoryById(hardwareRequest.getCategoryId());
        hardware.setCategory(hardwareCategory);
    }
}
