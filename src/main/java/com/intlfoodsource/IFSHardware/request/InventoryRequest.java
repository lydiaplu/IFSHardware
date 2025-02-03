package com.intlfoodsource.IFSHardware.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
    private Integer quantity;
    private String status;
    private LocalDateTime lastInbound;
    private Integer hardwareId;
    private Integer locationId;
}
