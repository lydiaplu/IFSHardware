package com.intlfoodsource.IFSHardware.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareDocumentRequest {
    private String type;
    private String fileUrl;
    private String description;
    private Integer hardwareId;
}
