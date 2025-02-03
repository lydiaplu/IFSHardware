package com.intlfoodsource.IFSHardware.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareRequest {
    private String name;
    private String specification;
    private String description;
    private Integer categoryId;
}
