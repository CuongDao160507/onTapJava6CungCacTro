package com.example.ontapdemau.controller;

import com.example.ontapdemau.dto.DonHangRequest;
import com.example.ontapdemau.dto.DonHangResponse;
import com.example.ontapdemau.entity.DonHang;
import com.example.ontapdemau.service.DonHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/don-hang")
public class DonHangController {
    @Autowired
    private DonHangService donHangService;

    @GetMapping("/getAllDisplay") //vue
    public List<DonHang> getAllDisplay(){
        return donHangService.getAllDisPlay();
    }

    @GetMapping("/display") //postman
    public List<DonHangResponse> display(){
        return donHangService.getAllDonHang();
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        return ResponseEntity.ok(donHangService.getAll(page));
    }

    @PostMapping("/add")
    public void addDonHang(@RequestBody @Valid DonHangRequest donHangRequest){
        donHangService.addDonHang(donHangRequest);
    }

    @PutMapping("/update")
    public void updateDonHang(@RequestBody @Valid DonHangRequest donHangRequest){
        donHangService.updateDonHang(donHangRequest);
    }

    @DeleteMapping("/delete")
    public void deleteDonHang(@RequestParam("id") Integer id){
        donHangService.deleteDonHang(id);
    }


}
