package com.example.demau.controller;

import com.example.demau.dto.DonHangRequest;
import com.example.demau.dto.DonHangResponse;
import com.example.demau.entity.DonHang;
import com.example.demau.service.DonHangSerivce;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/don-hang")
public class DonHangController {
    @Autowired
    private DonHangSerivce donHangSerivce;

    @GetMapping("/display")
    public List<DonHangResponse> display(){
        return donHangSerivce.getAllDonHang();
    }

    @PostMapping("/add")
    public void addDonHang(@RequestBody @Valid DonHangRequest donHangRequest){
        donHangSerivce.addDonHang(donHangRequest);
    }

    @PutMapping("/update")
    public void updateDonHang(@RequestBody @Valid DonHangRequest donHangRequest){
        donHangSerivce.updateDonHang(donHangRequest);
    }

    @DeleteMapping("/delete")
    public void deleteDonHang(@RequestParam("id")  Integer id){
        donHangSerivce.deleteDonHang(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDonHang(@RequestParam(name = "page", defaultValue = "0") Integer page){
        return ResponseEntity.ok(donHangSerivce.getAll(page));
    }
}
