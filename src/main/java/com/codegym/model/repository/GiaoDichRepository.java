package com.codegym.model.repository;

import com.codegym.model.entity.GiaoDich;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GiaoDichRepository extends JpaRepository<GiaoDich, String> {
    @Query("select s from GiaoDich s where s.isDeleted=false")
    Page<GiaoDich> findAllByDeletedIsFalse(Pageable pageable);

    @Query("select s from GiaoDich s where (s.khachHang.name like CONCAT('%',:search,'%') or s.dichVu.name like CONCAT('%',:search,'%')) and s.isDeleted = false")
    Page<GiaoDich> search(@Param("search") String search, Pageable pageable);
}
