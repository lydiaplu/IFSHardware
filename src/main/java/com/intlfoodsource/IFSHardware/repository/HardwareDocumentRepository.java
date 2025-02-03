package com.intlfoodsource.IFSHardware.repository;

import com.intlfoodsource.IFSHardware.model.HardwareDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HardwareDocumentRepository extends JpaRepository<HardwareDocument, Integer> {
    @Query("SELECT hd FROM HardwareDocument hd WHERE hd.hardware.id = :hardwareId")
    List<HardwareDocument> findHardwareDocumentByHardwareId(Integer hardwareId);
}
