package com.codegym.model.service.impl;

import com.codegym.model.entity.LoaiDichVu;
import com.codegym.model.repository.DichVuRepository;
import com.codegym.model.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DichVuServiceImpl implements DichVuService {
    @Autowired
    private DichVuRepository repository;
    @Override
    public List<LoaiDichVu> findAll() {
        return repository.findAll();
    }
}
