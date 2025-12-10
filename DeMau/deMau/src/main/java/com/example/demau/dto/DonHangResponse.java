package com.example.demau.dto;

import com.example.demau.entity.DonHang;
import com.example.demau.entity.KhachHang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonHangResponse {
    private Integer id;

    private String maDonHang;

    private LocalDate ngayDat;

    private Float tongTien;

//    private KhachHang khachHang;

    private String tenKhachHang;

    private String diaChi;

    public DonHangResponse(DonHang donHang) {
        this.id = donHang.getId();
        this.maDonHang = donHang.getMaDonHang();
        this.ngayDat = donHang.getNgayDat();
        this.tongTien = donHang.getTongTien();
        this.tenKhachHang = donHang.getKhachHang().getTenKhachHang();
        this.diaChi = donHang.getKhachHang().getDiaChi();
    }
}
