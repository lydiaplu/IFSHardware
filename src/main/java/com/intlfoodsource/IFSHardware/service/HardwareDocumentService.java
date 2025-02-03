package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.exception.ResourceNotFoundException;
import com.intlfoodsource.IFSHardware.mapper.HardwareDocumentMapper;
import com.intlfoodsource.IFSHardware.model.Hardware;
import com.intlfoodsource.IFSHardware.model.HardwareDocument;
import com.intlfoodsource.IFSHardware.repository.HardwareDocumentRepository;
import com.intlfoodsource.IFSHardware.request.HardwareDocumentRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HardwareDocumentService implements IHardwareDocumentService{
    private final HardwareDocumentMapper hardwareDocumentMapper;
    private final HardwareDocumentRepository hardwareDocumentRepository;
    private final HardwareService hardwareService;

    @Override
    @Transactional
    public HardwareDocument addHardwareDocument(HardwareDocumentRequest hardwareDocumentRequest) {
        HardwareDocument hardwareDocument = new HardwareDocument();
        setHardwareDocumentModel(hardwareDocument, hardwareDocumentRequest);

        // Add this new document to Hardware document list
        Hardware hardware = hardwareDocument.getHardware();
        hardware.addHardwareDocument(hardwareDocument);

        return hardwareDocumentRepository.save(hardwareDocument);
    }

    @Override
    @Transactional
    public HardwareDocument updateHardwareDocument(Integer id, HardwareDocumentRequest hardwareDocumentRequest) {
        HardwareDocument hardwareDocument = getHardwareDocumentById(id);

        // Get previous hardware
        Hardware previousHardware = hardwareDocument.getHardware();
        Integer previousHardwareId = previousHardware.getId();

        // Set request to model
        setHardwareDocumentModel(hardwareDocument, hardwareDocumentRequest);

        // Get current hardware
        Hardware currentHardware = hardwareDocument.getHardware();
        Integer currentHardwareId = hardwareDocumentRequest.getHardwareId();

        // Check whether the Hardware has changed
        if(!previousHardwareId.equals(currentHardwareId)) {
            // Remove the document from the previous Hardware
            previousHardware.removeHardwareDocument(hardwareDocument);
            // Set document to current Hardware
            currentHardware.addHardwareDocument(hardwareDocument);
        }

        return hardwareDocumentRepository.save(hardwareDocument);
    }

    @Override
    public HardwareDocument getHardwareDocumentById(Integer id) {
        return hardwareDocumentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hardware Document not found with id: " + id));
    }

    @Override
    public List<HardwareDocument> getHardwareDocumentAll() {
        return hardwareDocumentRepository.findAll(Sort.by("id").ascending());
    }

    @Override
    @Transactional
    public void deleteHardwareDocument(Integer id) {
        HardwareDocument hardwareDocument = getHardwareDocumentById(id);

        // Remove this document from Hardware document list
        Hardware hardware = hardwareDocument.getHardware();
        hardware.removeHardwareDocument(hardwareDocument);

        hardwareDocumentRepository.delete(hardwareDocument);
    }

    @Override
    public List<HardwareDocument> getHardwareDocumentByHardwareId(Integer hardwareId) {
        return hardwareDocumentRepository.findHardwareDocumentByHardwareId(hardwareId);
    }

    private void setHardwareDocumentModel(HardwareDocument hardwareDocument, HardwareDocumentRequest hardwareDocumentRequest) {
        hardwareDocumentMapper.toHardwareDocument(hardwareDocument, hardwareDocumentRequest);

        // Get Hardware model, and set connect with document
        Hardware hardware = hardwareService.getHardwareById(hardwareDocumentRequest.getHardwareId());
        hardwareDocument.setHardware(hardware);

        // Set date, I do not set date at front end
        hardwareDocument.setUpdatedAt(LocalDateTime.now());
        if(hardwareDocument.getCreatedAt() == null) {
            hardwareDocument.setCreatedAt(LocalDateTime.now());
        }
    }
}
