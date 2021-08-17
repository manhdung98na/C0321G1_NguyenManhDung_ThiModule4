package com.codegym.controller;

import com.codegym.dto.GiaoDichDTO;
import com.codegym.model.entity.GiaoDich;
import com.codegym.model.service.DichVuService;
import com.codegym.model.service.GiaoDichService;
import com.codegym.model.service.KhachHangService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("giao-dich")
public class GiaoDichController {
    @Autowired
    private GiaoDichService giaoDichService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private DichVuService dichVuService;

    @GetMapping("")
    public ModelAndView showList(@PageableDefault(value = 4) Pageable pageable) {
        Page<GiaoDich> list = giaoDichService.findAll(pageable);
        return new ModelAndView("giao_dich/list", "listGiaoDich", list);
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("giao_dich/create");
        modelAndView.addObject("giaoDich", new GiaoDichDTO());
        modelAndView.addObject("listKhachHang", khachHangService.findAll());
        modelAndView.addObject("listDichVu", dichVuService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("giaoDich") GiaoDichDTO giaoDichDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        new GiaoDichDTO().validate(giaoDichDTO, bindingResult);
        if (bindingResult.hasFieldErrors()){
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listDichVu", dichVuService.findAll());

            return "giao_dich/create";
        }
        if (giaoDichService.checkDuplicatedId(giaoDichDTO.getId())){
            bindingResult.rejectValue("id","id-duplicated-error");
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listDichVu", dichVuService.findAll());
            return "giao_dich/create";
        }
        GiaoDich newGiaoDich = new GiaoDich();
        BeanUtils.copyProperties(giaoDichDTO, newGiaoDich);
        newGiaoDich.setDeleted(false);
        if (giaoDichService.save(newGiaoDich) != null) {
            redirectAttributes.addFlashAttribute("status", "Success!");
            redirectAttributes.addFlashAttribute("statusContent", "Thêm mới giao dịch thành công!");
        } else {
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Thêm mới giao dịch thất bại!");
        }
        return "redirect:/giao-dich";
    }

    @GetMapping("/view/{id}")
    public ModelAndView showView(@PathVariable String id) {
        GiaoDichDTO giaoDichDTO = new GiaoDichDTO();
        Optional<GiaoDich> viewGiaoDich = giaoDichService.findById(id);
        if (!viewGiaoDich.isPresent()) {
            return new ModelAndView("error");
        }
        ModelAndView modelAndView = new ModelAndView("giao_dich/view");
        BeanUtils.copyProperties(viewGiaoDich.get(), giaoDichDTO);
        modelAndView.addObject("giaoDich", giaoDichDTO);
        modelAndView.addObject("listKhachHang", khachHangService.findAll());
        modelAndView.addObject("listDichVu", dichVuService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@Validated @ModelAttribute("giao-dich") GiaoDichDTO giaoDichDTO,BindingResult bindingResult,
                       RedirectAttributes redirectAttributes, Model model) {
        new GiaoDichDTO().validate(giaoDichDTO, bindingResult);
        if (bindingResult.hasFieldErrors()){
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Failure to edit");
            return "giao_dich/list";
        }
        GiaoDich giaoDich = new GiaoDich();
        BeanUtils.copyProperties(giaoDichDTO,giaoDich);
        if (giaoDichService.save(giaoDich) != null) {
            redirectAttributes.addFlashAttribute("status", "Success!");
            redirectAttributes.addFlashAttribute("statusContent", "Success to edit");
        } else {
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Failure to edit");
        }
        return "redirect:/giao-dich";
    }

    @GetMapping("/search")
    public String search(@RequestParam("input-search") String search, @PageableDefault(value = 4) Pageable pageable, Model model) {
        Page<GiaoDich> list = giaoDichService.search(search, pageable);
        model.addAttribute("listGiaoDich", list);
        model.addAttribute("searchContent", search);
        return "giao_dich/search";
    }
}
