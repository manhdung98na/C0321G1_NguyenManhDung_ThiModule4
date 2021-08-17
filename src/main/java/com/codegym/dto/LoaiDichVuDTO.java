package com.codegym.dto;

import com.codegym.model.entity.GiaoDich;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiDichVuDTO {
    private Integer id;
    private String name;
    private Set<GiaoDich> giaoDich;
}
