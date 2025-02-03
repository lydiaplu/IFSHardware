package com.intlfoodsource.IFSHardware.service;

import com.intlfoodsource.IFSHardware.model.HardwareDocument;
import com.intlfoodsource.IFSHardware.request.HardwareDocumentRequest;

import java.util.List;

public interface IHardwareDocumentService {
    HardwareDocument addHardwareDocument(HardwareDocumentRequest hardwareDocumentRequest);

    HardwareDocument updateHardwareDocument(Integer id, HardwareDocumentRequest hardwareDocumentRequest);

    HardwareDocument getHardwareDocumentById(Integer id);

    List<HardwareDocument> getHardwareDocumentAll();

    void deleteHardwareDocument(Integer id);

    List<HardwareDocument> getHardwareDocumentByHardwareId(Integer hardwareId);
}
