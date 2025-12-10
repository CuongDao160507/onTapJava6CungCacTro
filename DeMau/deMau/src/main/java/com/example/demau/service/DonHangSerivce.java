package com.example.demau.service;

import com.example.demau.dto.DonHangRequest;
import com.example.demau.dto.DonHangResponse;
import com.example.demau.entity.DonHang;
import com.example.demau.repository.DonHangRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonHangSerivce {
    @Autowired
    private DonHangRepository donHangRepository;

    public List<DonHangResponse> getAllDonHang() {
        return donHangRepository.findAll().stream().map(DonHangResponse::new).toList();
    }

    public void addDonHang(DonHangRequest donHangRequest) {
        DonHang donHang = new DonHang();
        BeanUtils.copyProperties(donHangRequest, donHang);
        donHangRepository.save(donHang);
    }

    public void updateDonHang(DonHangRequest donHangRequest) {
        DonHang donHang = new DonHang();
        BeanUtils.copyProperties(donHangRequest, donHang);
        donHangRepository.save(donHang);
    }

    public void deleteDonHang(Integer id) {
        donHangRepository.deleteById(id);
    }

    public Page<DonHang> getAll(Integer page) {
        Pageable pageable =  PageRequest.of(page, 3);
        return donHangRepository.findAll(pageable);
    }

}
