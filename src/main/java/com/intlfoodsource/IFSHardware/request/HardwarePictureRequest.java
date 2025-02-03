package com.intlfoodsource.IFSHardware.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwarePictureRequest {
    private String pictureUrl;
    private Integer hardwareId;
}
