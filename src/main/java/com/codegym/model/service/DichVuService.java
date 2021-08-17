package com.codegym.model.service;

import com.codegym.model.entity.KhachHang;
import com.codegym.model.entity.LoaiDichVu;

import java.util.List;

public interface DichVuService {
    List<LoaiDichVu> findAll();
}
