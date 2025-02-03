package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.exception.ResourceNotFoundException;
import com.intlfoodsource.IFSHardware.mapper.HardwarePictureMapper;
import com.intlfoodsource.IFSHardware.model.Hardware;
import com.intlfoodsource.IFSHardware.model.HardwarePicture;
import com.intlfoodsource.IFSHardware.repository.HardwarePictureRepository;
import com.intlfoodsource.IFSHardware.request.HardwarePictureRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HardwarePictureService implements IHardwarePictureService{
    private final HardwarePictureMapper hardwarePictureMapper;
    private final HardwarePictureRepository hardwarePictureRepository;
    private final HardwareService hardwareService;

    @Override
    @Transactional
    public HardwarePicture addHardwarePicture(HardwarePictureRequest hardwarePictureRequest) {
        HardwarePicture hardwarePicture = new HardwarePicture();
        setHardwarePictureModel(hardwarePicture, hardwarePictureRequest);

        // Add this new picture to Hardware picture list
        Hardware hardware= hardwarePicture.getHardware();
        hardware.addHardwarePicture(hardwarePicture);

        return hardwarePictureRepository.save(hardwarePicture);
    }

    @Override
    @Transactional
    public HardwarePicture updateHardwarePicture(Integer id, HardwarePictureRequest hardwarePictureRequest) {
        HardwarePicture hardwarePicture = getHardwarePictureById(id);

        // Get previous hardware
        Hardware previousHardware = hardwarePicture.getHardware();
        Integer previousHardwareId = previousHardware.getId();

        // Set request to model
        setHardwarePictureModel(hardwarePicture, hardwarePictureRequest);

        // Get current hardware
        Hardware currentHardware = hardwarePicture.getHardware();
        Integer currentHardwareId = currentHardware.getId();

        // Check whether the Hardware has changed
        if(!previousHardwareId.equals(currentHardwareId)) {
            // Remove the picture from the previous Hardware
            previousHardware.removeHardwarePicture(hardwarePicture);
            // Set picture to current Hardware
            currentHardware.addHardwarePicture(hardwarePicture);
        }

        return hardwarePictureRepository.save(hardwarePicture);
    }

    @Override
    public HardwarePicture getHardwarePictureById(Integer id) {
        return hardwarePictureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hardware Picture not found with id: " + id));
    }

    @Override
    public List<HardwarePicture> getHardwarePictureAll() {
        return hardwarePictureRepository.findAll(Sort.by("id").ascending());
    }

    @Override
    public void deleteHardwarePicture(Integer id) {
        HardwarePicture hardwarePicture = getHardwarePictureById(id);

        // Remove this picture from Hardware picture list
        Hardware hardware = hardwarePicture.getHardware();
        hardware.removeHardwarePicture(hardwarePicture);

        hardwarePictureRepository.delete(hardwarePicture);
    }

    @Override
    public List<HardwarePicture> getHardwarePictureByHardwareId(Integer hardwareId) {
        return List.of();
    }

    private void setHardwarePictureModel(HardwarePicture hardwarePicture, HardwarePictureRequest hardwarePictureRequest) {
        hardwarePictureMapper.toHardwarePicture(hardwarePicture, hardwarePictureRequest);

        Hardware hardware = hardwareService.getHardwareById(hardwarePictureRequest.getHardwareId());
        hardwarePicture.setHardware(hardware);
    }
}
