<template>
  <table class="table">
    <thead>
      <tr>
        <th scope="col">id</th>
        <th scope="col">maNhanVien</th>
        <th scope="col">hoTen</th>
        <th scope="col">ngaySinh</th>
        <th scope="col">gioiTinh</th>
        <th scope="col">maChucVu</th>
        <th scope="col">tenChucVu</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="nhanVien in listNhanVien" :key="nhanVien.id">
        <td>{{ nhanVien.id }}</td>
        <td>{{ nhanVien.maNhanVien }}</td>
        <td>{{ nhanVien.hoTen }}</td>
        <td>{{ nhanVien.ngaySinh }}</td>
        <td>{{ nhanVien.gioiTinh == true ? "TuanThanDen" : "TraMyCoDon" }}</td>
        <td>{{ nhanVien.chucVu.maChucVu }}</td>
        <td>{{ nhanVien.chucVu.tenChucVu }}</td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { fetchAllNhanVien } from "@/service/NhanVienService";

const listNhanVien = ref([]);

const fetchGetAllNhanVien = async () => {
  try {
    listNhanVien.value = await fetchAllNhanVien();
  } catch (error) {
    console.log(error);
  }
};
onMounted(fetchGetAllNhanVien);
</script>
