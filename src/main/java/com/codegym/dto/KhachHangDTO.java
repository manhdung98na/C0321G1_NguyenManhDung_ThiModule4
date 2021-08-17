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
public class KhachHangDTO {
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private boolean isDeleted;

    private Set<GiaoDich> giaoDich;
}
