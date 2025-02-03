package com.intlfoodsource.IFSHardware.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HardwarePictureResponse {
    private Integer id;
    private String pictureUrl; // Represent the Blob as a Base64 encoded string or URL
    private HardwareResponse hardware;

    public HardwarePictureResponse(Integer id, String pictureUrl, HardwareResponse hardware) {
        this.id = id;
        this.pictureUrl = pictureUrl != null ? pictureUrl : ""; // Handle null safely
        this.hardware = hardware;
    }
}
