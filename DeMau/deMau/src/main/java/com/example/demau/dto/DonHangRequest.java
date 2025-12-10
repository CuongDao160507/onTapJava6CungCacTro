package com.example.demau.dto;

import com.example.demau.entity.KhachHang;
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
public class DonHangRequest {
    private Integer id;

    @NotBlank(message = "Ma don hang khong duoc de trong.")
    private String maDonHang;

    @NotNull(message = "Ngay dat khong duoc de trong.")
    private LocalDate ngayDat;

    @NotNull(message = "Tong tien khong duoc de trong.")
    private Float tongTien;

    private KhachHang khachHang;
}
