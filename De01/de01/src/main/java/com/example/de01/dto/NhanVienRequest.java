package com.example.de01.dto;

import com.example.de01.entity.ChucVu;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienRequest {
    private Integer id;

    @NotBlank(message = "Ma nhan vien khong de trong")
    private String maNhanVien; //ko hien thi - luc ADD/UPDATE -> Van can.

    @NotBlank(message = "Ma nhan vien khong de trong")
    private String hoTen;

    @NotNull(message = "Ngay sinh khong de trong")
    private LocalDate ngaySinh;

    @NotNull(message = "Gioi tinh  khong de trong")
    private Boolean gioiTinh;

    private ChucVu chucVu;
}
