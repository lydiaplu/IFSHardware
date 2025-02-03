package com.intlfoodsource.IFSHardware.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareDocumentResponse {
    private Integer id;
    private String type;
    private String fileUrl;
    private String description;
    private String createdAt; // LocalDateTime formatted as a String
    private String updatedAt;
    private HardwareResponse hardware;

    public HardwareDocumentResponse(Integer id, String type, String fileUrl, String description,
                                    LocalDateTime createdAt, LocalDateTime updatedAt, HardwareResponse hardware) {
        this.id = id;
        this.type = type != null ? type : ""; // Ensure no null values
        this.fileUrl = fileUrl != null ? fileUrl : "";
        this.description = description != null ? description : "";
        this.createdAt = createdAt != null ? String.valueOf(createdAt) : ""; // Use String.valueOf() for null safety
        this.updatedAt = updatedAt != null ? String.valueOf(updatedAt) : ""; // Use String.valueOf() for null safety
        this.hardware = hardware;
    }
}
