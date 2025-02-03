package com.intlfoodsource.IFSHardware.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HardwareLocationResponse {
    private Integer id;
    private String name;
    private String description;
    private HardwareLocationResponse parentLocation; // Recursive parent location
    private List<HardwareLocationResponse> subLocations; // List of sub-locations

    public HardwareLocationResponse(Integer id, String name, String description,
                                    HardwareLocationResponse parentLocation,
                                    List<HardwareLocationResponse> subLocations) {
        this.id = id;
        this.name = name != null ? name : ""; // Handle null values
        this.description = description != null ? description : ""; // Handle null values
        this.parentLocation = parentLocation;
        this.subLocations = subLocations;
    }
}
