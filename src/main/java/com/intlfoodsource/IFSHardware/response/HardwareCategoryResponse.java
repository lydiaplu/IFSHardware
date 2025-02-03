package com.intlfoodsource.IFSHardware.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HardwareCategoryResponse {
    private Integer id;
    private String name;

    public HardwareCategoryResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
