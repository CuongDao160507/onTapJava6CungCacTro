package com.example.de01.service;

import com.example.de01.dto.NhanVienRequest;
import com.example.de01.dto.NhanVienResponse;
import com.example.de01.entity.NhanVien;
import com.example.de01.repository.NhanVienRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {
    @Autowired
    NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAllDisplay() {
        return nhanVienRepository.findAll();
    }

    public List<NhanVienResponse> getAllNhanVien() {
        return nhanVienRepository.findAll().stream().map(NhanVienResponse::new).toList();
    }

    public void addNhanVien(NhanVienRequest nhanVienRequest) {
        NhanVien nhanVien = new NhanVien();
        BeanUtils.copyProperties(nhanVienRequest, nhanVien);
        nhanVienRepository.save(nhanVien);
    }

    public void updateNhanVien(NhanVienRequest nhanVienRequest) {
        NhanVien nhanVien = new NhanVien();
        BeanUtils.copyProperties(nhanVienRequest, nhanVien);
        nhanVienRepository.save(nhanVien);
    }

    public void deleteNhanVien(Integer id) {
        nhanVienRepository.deleteById(id);
    }

//    them cho vui - co the ko co trong de - cam thay ko dc thi ko can on (detail)
    public NhanVienResponse getNhanVienById(Integer id) {
        NhanVien nhanVien = nhanVienRepository.findById(id).get();
        return new NhanVienResponse(nhanVien);
    }

    public Page<NhanVien> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 3);
        return nhanVienRepository.findAll(pageable);
    }
}
