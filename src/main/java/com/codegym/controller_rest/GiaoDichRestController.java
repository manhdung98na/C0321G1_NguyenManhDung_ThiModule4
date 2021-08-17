package com.codegym.controller_rest;

import com.codegym.model.entity.KhachHang;
import com.codegym.model.service.GiaoDichService;
import com.codegym.model.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/giao-dich")
public class GiaoDichRestController {
    @Autowired
    private GiaoDichService service;
    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/khach-hang/{id}")
    private ResponseEntity<String> getInfo(@PathVariable Integer id) {
        String result = "";
        Optional<KhachHang> khachHang = khachHangService.findById(id);
        if (khachHang.isPresent()) {
            result = khachHang.get().getPhone() + "," + khachHang.get().getEmail();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/multi-delete")
    public ResponseEntity<Void> deleteMulti(@RequestBody String data) {
        System.err.println("delete");
        String idStringConCat = "";
        Pattern p = Pattern.compile(":\\[(.*?)]}");
        Matcher m = p.matcher(data);
        while (m.find()) {
            idStringConCat = m.group(1);
        }
        String[] idArray = idStringConCat.replace("\"", "").split(",");
        for (String id : idArray) {
            service.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
