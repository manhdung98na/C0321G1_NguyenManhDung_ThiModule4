package com.codegym.model.service;

import com.codegym.model.entity.KhachHang;

import java.util.List;
import java.util.Optional;

public interface KhachHangService {
    List<KhachHang> findAll();

    Optional<KhachHang> findById(Integer id);
}
