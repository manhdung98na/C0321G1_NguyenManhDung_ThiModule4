package com.codegym.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDich {
    @Id
    private String id;

    @ManyToOne(targetEntity = KhachHang.class)
    @JoinColumn(name = "id_khach_hang", referencedColumnName = "id")
    private KhachHang khachHang;

    private String date;

    @ManyToOne(targetEntity = LoaiDichVu.class)
    @JoinColumn(name = "id_dich_vu", referencedColumnName = "id")
    private LoaiDichVu dichVu;

    private Double cost;

    private Double area;

    private boolean isDeleted;
}
