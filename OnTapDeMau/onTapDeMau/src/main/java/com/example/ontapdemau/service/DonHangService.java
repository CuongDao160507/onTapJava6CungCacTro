package com.example.ontapdemau.service;

import com.example.ontapdemau.dto.DonHangRequest;
import com.example.ontapdemau.dto.DonHangResponse;
import com.example.ontapdemau.entity.DonHang;
import com.example.ontapdemau.repository.DonHangRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonHangService {
    @Autowired
    private DonHangRepository donHangRepository;

    public List<DonHang> getAllDisPlay() {
        return donHangRepository.findAll();
    }

    public List<DonHangResponse> getAllDonHang() {
        return donHangRepository.findAll().stream().map(DonHangResponse::new).toList();
    }

    public DonHangResponse getDonHangById(Integer id) {
        DonHang donHang = donHangRepository.findById(id).get();
        return new DonHangResponse(donHang);
    }

    public Page<DonHang> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 3);
        return  donHangRepository.findAll(pageable);
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
}
