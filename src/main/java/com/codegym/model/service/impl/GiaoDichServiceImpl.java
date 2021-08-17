package com.codegym.model.service.impl;

import com.codegym.model.entity.GiaoDich;
import com.codegym.model.repository.GiaoDichRepository;
import com.codegym.model.service.GiaoDichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GiaoDichServiceImpl implements GiaoDichService {
    @Autowired
    private GiaoDichRepository repository;

    @Override
    public Page<GiaoDich> findAll(Pageable pageable) {
        return repository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public GiaoDich save(GiaoDich suatChieu) {
        return repository.save(suatChieu);
    }

    @Override
    public void delete(String id) {
        GiaoDich suatChieu = repository.getById(id);
        suatChieu.setDeleted(true);
        repository.save(suatChieu);
    }

    @Override
    public boolean checkDuplicatedId(String id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<GiaoDich> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Page<GiaoDich> search(String search, Pageable pageable) {
        return repository.search(search, pageable);
    }
}
