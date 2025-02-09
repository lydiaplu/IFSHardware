package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.exception.ResourceNotFoundException;
import com.intlfoodsource.IFSHardware.mapper.HardwareMapper;
import com.intlfoodsource.IFSHardware.model.Hardware;
import com.intlfoodsource.IFSHardware.model.HardwareCategory;
import com.intlfoodsource.IFSHardware.repository.HardwareRepository;
import com.intlfoodsource.IFSHardware.request.HardwareRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
        Hardware hardware = getHardwareById(id);
        setHardwareModel(hardware, hardwareRequest);

        return hardwareRepository.save(hardware);
    }

    @Override
    public Hardware getHardwareById(Integer id) {
        return hardwareRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hardware not found with id: " + id));
    }

    @Override
    public List<Hardware> getHardwareAll() {
        return hardwareRepository.findAll(Sort.by("id").ascending());
    }

    @Override
    public void deleteHardware(Integer id) {
        Hardware hardware = getHardwareById(id);
        hardwareRepository.delete(hardware);
    }

    private void setHardwareModel(Hardware hardware, HardwareRequest hardwareRequest) {
        hardwareMapper.toHardware(hardware, hardwareRequest);

        HardwareCategory hardwareCategory = hardwareCategoryService.getHardwareCategoryById(hardwareRequest.getCategoryId());
        hardware.setCategory(hardwareCategory);
    }
}
