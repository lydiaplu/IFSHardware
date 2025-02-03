package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.exception.ConflictException;
import com.intlfoodsource.IFSHardware.exception.ResourceNotFoundException;
import com.intlfoodsource.IFSHardware.mapper.HardwareCategoryMapper;
import com.intlfoodsource.IFSHardware.model.HardwareCategory;
import com.intlfoodsource.IFSHardware.repository.HardwareCategoryRepository;
import com.intlfoodsource.IFSHardware.request.HardwareCategoryRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HardwareCategoryService implements IHardwareCategoryService{
    private final HardwareCategoryMapper hardwareCategoryMapper;
    private final HardwareCategoryRepository hardwareCategoryRepository;

    @Override
    public HardwareCategory addHardwareCategory(HardwareCategoryRequest hardwareCategoryRequest) {
        HardwareCategory hardwareCategory = new HardwareCategory();// request -> model
        setHardwareCategoryModel(hardwareCategory, hardwareCategoryRequest);
        return hardwareCategoryRepository.save(hardwareCategory);
    }

    @Override
    @Transactional
    public HardwareCategory updateHardwareCategory(Integer id, HardwareCategoryRequest hardwareCategoryRequest) {
        HardwareCategory hardwareCategory = getHardwareCategoryById(id);
        setHardwareCategoryModel(hardwareCategory, hardwareCategoryRequest);
        return hardwareCategoryRepository.save(hardwareCategory);
    }

    @Override
    public HardwareCategory getHardwareCategoryById(Integer id) {
        return hardwareCategoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hardware Category not found with id: " + id));
    }

    @Override
    public List<HardwareCategory> getHardwareCategoryAll() {
        return hardwareCategoryRepository.findAll(Sort.by("id").ascending());
    }

    @Override
    public void deleteHardwareCategory(Integer id) {
        try {
            HardwareCategory hardwareCategory = getHardwareCategoryById(id);
            hardwareCategoryRepository.delete(hardwareCategory);
        }catch (DataIntegrityViolationException ex) {
            throw new ConflictException("Cannot delete category as it is linked to existing hardware.");
        }
    }

    private void setHardwareCategoryModel(HardwareCategory hardwareCategory, HardwareCategoryRequest hardwareCategoryRequest) {
        hardwareCategoryMapper.toHardwareCategory(hardwareCategory, hardwareCategoryRequest);
    }
}
