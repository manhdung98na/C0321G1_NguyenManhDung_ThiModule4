package com.codegym.dto;

import com.codegym.model.entity.GiaoDich;
import com.codegym.model.entity.KhachHang;
import com.codegym.model.entity.LoaiDichVu;
import lombok.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDichDTO implements Validator {
    private String id;

    private KhachHang khachHang;

    private String date;

    private LoaiDichVu dichVu;

    private Double cost;

    private Double area;

    private boolean isDeleted;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @SneakyThrows
    @Override
    public void validate(Object target, Errors errors) {
        GiaoDichDTO giaoDichDTO = (GiaoDichDTO) target;
        String patternID = "^MGD-[0-9]{4}$";
        if (!Pattern.matches(patternID, giaoDichDTO.id)) {
            errors.rejectValue("id", "id-error");
        }
        if (giaoDichDTO.cost <= 500000) {
            errors.rejectValue("cost", "cost-error");
        }
        if (giaoDichDTO.area <= 20) {
            errors.rejectValue("area", "area-error");
        }
        Date dateGiaoDich = new SimpleDateFormat("yyyy-MM-dd").parse(giaoDichDTO.date);
        if (dateGiaoDich.before(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()))) {
            errors.rejectValue("date", "date-error");
        }

    }
}
