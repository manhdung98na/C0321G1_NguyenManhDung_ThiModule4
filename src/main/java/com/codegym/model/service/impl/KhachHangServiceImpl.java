package com.codegym.model.service.impl;

import com.codegym.model.entity.KhachHang;
import com.codegym.model.repository.KhachHangRepository;
import com.codegym.model.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository repository;

    @Override
    public List<KhachHang> findAll() {
        return repository.findAllByDeletedIsFalse();
    }

    @Override
    public Optional<KhachHang> findById(Integer id) {
        return repository.findById(id);
    }
}
