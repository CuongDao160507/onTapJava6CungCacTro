<template>
  <h2>Order information</h2>
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
        <td>{{ donHang.tenKhachHang }}</td>
        <td>{{ donHang.diaChi }}</td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { fetchAllDonHang } from "@/service/DonHangService";

const listDonHang = ref([]);

const handleFetchAllDonHang = async () => {
  try {
    listDonHang.value = await fetchAllDonHang();
  } catch (error) {
    console.log(error);
  }
};
onMounted(handleFetchAllDonHang);
</script>
