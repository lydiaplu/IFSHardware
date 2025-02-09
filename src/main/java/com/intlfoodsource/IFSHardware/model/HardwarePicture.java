package com.intlfoodsource.IFSHardware.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "hardware_picture")
public class HardwarePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String pictureUrl;

    @ManyToOne
    @JoinColumn(name = "hardware_id", nullable = false)
    @JsonBackReference("pictures")
    private Hardware hardware;

    public HardwarePicture() {

    }
}
