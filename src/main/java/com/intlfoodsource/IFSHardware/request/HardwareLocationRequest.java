package com.intlfoodsource.IFSHardware.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareLocationRequest {
    private String type;
    private String name;
    private String description;
    private Integer parentLocationId;
}
