<template>
  <table class="table">
    <thead>
      <tr>
        <th scope="col">id</th>
        <th scope="col">maDonHang</th>
        <th scope="col">ngayDat</th>
        <th scope="col">tongTien</th>
        <th scope="col">tenKhachHang</th>
        <th scope="col">diaChi</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="donHang in listDonHang" :key="donHang.id">
        <td>{{ donHang.id }}</td>
        <td>{{ donHang.maDonHang }}</td>
        <td>{{ donHang.ngayDat }}</td>
        <td>{{ donHang.tongTien }}</td>
        <td>{{ donHang.khachHang.tenKhachHang }}</td>
        <td>{{ donHang.khachHang.diaChi }}</td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { fetchAllDonHang } from "../service/DemoService.js";
const listDonHang = ref([]);

const handleAllDonHang = async () => {
  try {
    listDonHang.value = await fetchAllDonHang();
  } catch (error) {
    console.error(error);
  }
};
onMounted(handleAllDonHang);
</script>

<style lang="scss" scoped></style>
