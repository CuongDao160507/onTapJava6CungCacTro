package com.example.de01.dto;

import com.example.de01.entity.ChucVu;
import com.example.de01.entity.NhanVien;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"maNhanVien", "hoTen", "gioiTinh", "ngaySinh", "maChucVu", "tenChucVu"})
public class NhanVienResponse {
//    private Integer id;

    private String maNhanVien;

    private String hoTen;

    private LocalDate ngaySinh;

    private Boolean gioiTinh;

//    private ChucVu chucVu;

    private String maChucVu;

    private String tenChucVu;


    public NhanVienResponse(NhanVien nhanVien) {
        this.maNhanVien = nhanVien.getMaNhanVien();
        this.hoTen = nhanVien.getHoTen();
        this.ngaySinh =  nhanVien.getNgaySinh();
        this.gioiTinh = nhanVien.getGioiTinh();
        this.maChucVu = nhanVien.getChucVu().getMaChucVu();
        this.tenChucVu = nhanVien.getChucVu().getTenChucVu();
    }
}
