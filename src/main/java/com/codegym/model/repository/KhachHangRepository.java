package com.codegym.model.repository;

import com.codegym.model.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    @Query("select p from KhachHang p where p.isDeleted=false")
    List<KhachHang> findAllByDeletedIsFalse();
}
