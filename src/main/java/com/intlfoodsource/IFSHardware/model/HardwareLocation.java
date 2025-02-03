package com.intlfoodsource.IFSHardware.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "hardware_location")
public class HardwareLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private HardwareLocation parentLocation;

    @OneToMany(mappedBy = "parentLocation",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<HardwareLocation> subLocations;

    public HardwareLocation() {
        subLocations = new ArrayList<>();
    }

    public void addSubLocations(HardwareLocation subLocation) {
        this.subLocations.add(subLocation);
        subLocation.setParentLocation(this);
    }

    public void removeSubLocations(HardwareLocation subLocation) {
        this.subLocations.remove(subLocation);
        subLocation.setParentLocation(null);
    }
}
