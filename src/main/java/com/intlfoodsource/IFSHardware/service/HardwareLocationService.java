package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.exception.ResourceNotFoundException;
import com.intlfoodsource.IFSHardware.mapper.HardwareLocationMapper;
import com.intlfoodsource.IFSHardware.model.Hardware;
import com.intlfoodsource.IFSHardware.model.HardwareLocation;
import com.intlfoodsource.IFSHardware.repository.HardwareLocationRepository;
import com.intlfoodsource.IFSHardware.request.HardwareLocationRequest;
import com.intlfoodsource.IFSHardware.utils.CommonUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HardwareLocationService implements IHardwareLocationService {
    private final HardwareLocationMapper hardwareLocationMapper;
    private final HardwareLocationRepository hardwareLocationRepository;

    @Override
    @Transactional
    public HardwareLocation addHardwareLocation(HardwareLocationRequest hardwareLocationRequest) {
        HardwareLocation hardwareLocation = new HardwareLocation(); // request -> model
        setHardwareLocationModel(hardwareLocation, hardwareLocationRequest);

        // Add this new subLocation to parent location's subLocation list
        HardwareLocation parentLocation = hardwareLocation.getParentLocation();
        if (parentLocation != null) {
            parentLocation.addSubLocations(hardwareLocation);
        }

        return hardwareLocationRepository.save(hardwareLocation);
    }

    @Override
    @Transactional
    public HardwareLocation updateHardwareLocation(Integer id, HardwareLocationRequest hardwareLocationRequest) {
        HardwareLocation hardwareLocation = getHardwareLocationById(id);

        // Get previous parent location
        HardwareLocation previousParentLocation = hardwareLocation.getParentLocation();
        Integer previousParentLocationId = previousParentLocation != null ? previousParentLocation.getId() : null;

        // Set request to model
        setHardwareLocationModel(hardwareLocation, hardwareLocationRequest);

        // Get current parent location
        HardwareLocation currentParentLocation = hardwareLocation.getParentLocation();
        Integer currentParentLocationId = hardwareLocationRequest.getParentLocationId();

        // Check whether the parent location has changed
        if (!CommonUtils.equalsWithNullCheck(previousParentLocationId, currentParentLocationId)) {
            if(previousParentLocation!=null){
                // Remove the location from the previous parent location
                previousParentLocation.removeSubLocations(hardwareLocation);
            }

            if(currentParentLocation!=null){
                // Set location to current parent location
                currentParentLocation.addSubLocations(hardwareLocation);
            }
        }

        return hardwareLocationRepository.save(hardwareLocation);
    }

    @Override
    public HardwareLocation getHardwareLocationById(Integer id) {
        return hardwareLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hardware Location not found with id: " + id));
    }

    @Override
    public List<HardwareLocation> getHardwareLocationAll() {
        return hardwareLocationRepository.findAll(Sort.by("id").ascending());
    }

    @Override
    @Transactional
    public void deleteHardwareLocation(Integer id) {
        HardwareLocation hardwareLocation = getHardwareLocationById(id); // Validate if the location exists

        // Remove this location from parent list
        HardwareLocation parentLocation = hardwareLocation.getParentLocation();
        if(parentLocation != null) {
            parentLocation.removeSubLocations(hardwareLocation);
        }

        hardwareLocationRepository.delete(hardwareLocation);
    }

    private void setHardwareLocationModel(HardwareLocation hardwareLocation, HardwareLocationRequest hardwareLocationRequest) {
        hardwareLocationMapper.toHardwareLocation(hardwareLocation, hardwareLocationRequest);

        if (hardwareLocationRequest.getParentLocationId() != null) {
            HardwareLocation parentHardwareLocation = getHardwareLocationById(hardwareLocationRequest.getParentLocationId());
            hardwareLocation.setParentLocation(parentHardwareLocation);
        }
    }
}
