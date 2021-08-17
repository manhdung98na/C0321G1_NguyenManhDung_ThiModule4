package com.codegym.model.service;

import com.codegym.model.entity.GiaoDich;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GiaoDichService {
    Page<GiaoDich> findAll(Pageable pageable);

    GiaoDich save(GiaoDich suatChieu);

    void delete(String id);

    boolean checkDuplicatedId(String id);

    Optional<GiaoDich> findById(String id);

    Page<GiaoDich> search(String search, Pageable pageable);
}
