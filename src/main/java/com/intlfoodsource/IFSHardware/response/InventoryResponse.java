package com.intlfoodsource.IFSHardware.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoryResponse {
    private Integer id;
    private Integer quantity;
    private String status;
    private String lastInbound;
    private HardwareResponse hardware;
    private HardwareLocationResponse location;

    public InventoryResponse(Integer id, Integer quantity, String status, String lastInbound,
                             HardwareResponse hardware, HardwareLocationResponse location) {
        this.id = id;
        this.quantity = quantity;
        this.status = status != null ? status : "";
        this.lastInbound = lastInbound != null ? lastInbound : "";
        this.hardware = hardware;
        this.location = location;
    }
}
