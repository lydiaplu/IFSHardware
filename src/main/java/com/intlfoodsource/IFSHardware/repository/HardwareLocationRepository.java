package com.intlfoodsource.IFSHardware.repository;

import com.intlfoodsource.IFSHardware.model.HardwareLocation;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareLocationRepository extends JpaRepository<HardwareLocation, Integer> {
}
