package com.codegym.model.repository;

import com.codegym.model.entity.LoaiDichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DichVuRepository extends JpaRepository<LoaiDichVu, Integer> {
}
