package com.intlfoodsource.IFSHardware.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "hardware")
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String specification;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private HardwareCategory category;

    @OneToOne(mappedBy = "hardware",
            cascade = CascadeType.ALL
    )
    @JsonBackReference("hardware")
    private Inventory inventory;

    @OneToMany(mappedBy = "hardware",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference("pictures")
    private List<HardwarePicture> hardwarePictures;

    @OneToMany(mappedBy = "hardware",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference("documents")
    private List<HardwareDocument> hardwareDocuments;

    public Hardware() {
        this.hardwarePictures = new ArrayList<>();
        this.hardwareDocuments = new ArrayList<>();
    }

    public void addHardwarePicture(HardwarePicture hardwarePicture) {
        this.hardwarePictures.add(hardwarePicture);
        hardwarePicture.setHardware(this);
    }

    public void removeHardwarePicture(HardwarePicture hardwarePicture) {
        this.hardwarePictures.remove(hardwarePicture);
        hardwarePicture.setHardware(null);
    }

    public void addHardwareDocument(HardwareDocument hardwareDocument) {
        this.hardwareDocuments.add(hardwareDocument);
        hardwareDocument.setHardware(this);
    }

    public void removeHardwareDocument(HardwareDocument hardwareDocument) {
        this.hardwareDocuments.remove(hardwareDocument);
        hardwareDocument.setHardware(null);
    }
}
