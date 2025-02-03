package com.intlfoodsource.IFSHardware.model;

import com.intlfoodsource.IFSHardware.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InventoryStatus status;

    @Column(nullable = false)
    private LocalDateTime lastInbound;

    @OneToOne
    @JoinColumn(name = "hardware_id", nullable = false)
    private Hardware hardware;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private HardwareLocation location;

    public Inventory() {

    }
}
