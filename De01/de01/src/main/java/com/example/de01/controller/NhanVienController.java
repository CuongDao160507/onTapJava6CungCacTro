package com.example.de01.controller;

import com.example.de01.dto.NhanVienRequest;
import com.example.de01.dto.NhanVienResponse;
import com.example.de01.entity.NhanVien;
import com.example.de01.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/getAllDisplay") //vue
    public List<NhanVien> getAllDisplay() {
        return nhanVienService.getAllDisplay();
    }

    @GetMapping("/display") //postman
    public List<NhanVienResponse> getAllNhanVien() {
        return nhanVienService.getAllNhanVien();
    }

    @GetMapping("/getAll") //pagination
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page) {
        return ResponseEntity.ok(nhanVienService.getAll(page));
    }

    @GetMapping("/detail")
    public NhanVienResponse detailNhanVien(@RequestParam("id") Integer id) {
        return nhanVienService.getNhanVienById(id);
    }

    @PostMapping("/add")
    public void addNhanVien(@RequestBody @Valid NhanVienRequest nhanVienRequest) {
        nhanVienService.addNhanVien(nhanVienRequest);
    }

    @PutMapping("/update")
    public void updateNhanVien(@RequestBody @Valid NhanVienRequest nhanVienRequest) {
        nhanVienService.updateNhanVien(nhanVienRequest);
    }

    @DeleteMapping("/delete")
    public void deleteNhanVien(@RequestParam("id") Integer id) {
        nhanVienService.deleteNhanVien(id);
    }
}
