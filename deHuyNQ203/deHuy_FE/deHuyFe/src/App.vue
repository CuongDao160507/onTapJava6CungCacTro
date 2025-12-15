<template>
  <div class="container">
    <h1>Quản Lý Nhân Viên (Vue 3 + Spring Boot)</h1>

    <div class="card">
      <h3>{{ isEditing ? "Cập nhật nhân viên" : "Thêm mới nhân viên" }}</h3>
      <form @submit.prevent="saveEmployee">
        <div class="form-group">
          <label>Tên:</label>
          <input v-model="form.name" required placeholder="Nhập tên..." />
        </div>
        <div class="form-group">
          <label>Lương:</label>
          <input v-model="form.salary" type="number" required />
        </div>
        <div class="form-group">
          <label>Phòng ban (ID):</label>
          <select v-model="form.departmentId">
            <option :value="1">Phòng 1</option>
            <option :value="2">Phòng 2</option>
            <option :value="3">Phòng 3</option>
          </select>
        </div>
        <div class="form-group">
          <label>Email:</label>
          <input
            v-model="form.email"
            type="email"
            required
            placeholder="abc@gmail.com"
          />
        </div>

        <button type="submit">
          {{ isEditing ? "Lưu thay đổi" : "Thêm mới" }}
        </button>
        <button
          type="button"
          v-if="isEditing"
          @click="resetForm"
          class="btn-cancel"
        >
          Hủy
        </button>
      </form>
    </div>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Tên</th>
          <th>Email</th>
          <th>Lương</th>
          <th>Phòng Ban</th>
          <th>Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="emp in employees" :key="emp.id">
          <td>{{ emp.id }}</td>
          <td>{{ emp.name }}</td>
          <td>{{ emp.email }}</td>
          <td>{{ emp.salary }}</td>
          <td>{{ emp.department ? emp.department.name : "N/A" }}</td>
          <td>
            <button @click="editEmployee(emp)" class="btn-edit">Sửa</button>
            <button @click="deleteEmployee(emp.id)" class="btn-delete">
              Xóa
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

// --- CẤU HÌNH ---
const API_URL = "http://localhost:8080/employee";
// Tạo "chìa khóa" để đăng nhập (admin:234)
const authHeader = "Basic " + btoa("admin:234");

const employees = ref([]);
const isEditing = ref(false);
const form = ref({ id: null, name: "", email: "", salary: 0, departmentId: 1 });

// --- 1. LẤY DANH SÁCH (GET) ---
const fetchEmployees = async () => {
  try {
    // Gọi API getAllDisplay hoặc getAll đều được
    const res = await axios.get(`${API_URL}/getAllDisplay`, {
      headers: { Authorization: authHeader },
    });
    // Vì backend trả về ApiResponse (có status, message, data) nên phải lấy .data.data
    employees.value = res.data.data || res.data;
  } catch (error) {
    console.error("Lỗi tải data:", error);
    alert("Không tải được dữ liệu! Check console đi.");
  }
};

// --- 2. XÓA (DELETE) ---
const deleteEmployee = async (id) => {
  if (!confirm("Xóa thật nha?")) return;
  try {
    // API của em là /delete?id=...
    await axios.delete(`${API_URL}/delete?id=${id}`, {
      headers: { Authorization: authHeader },
    });
    alert("Xóa xong!");
    fetchEmployees(); // Tải lại bảng
  } catch (error) {
    alert("Lỗi xóa: " + error.response?.data?.message || error.message);
  }
};

// --- 3. THÊM HOẶC SỬA (POST / PUT) ---
const saveEmployee = async () => {
  // Chuẩn bị dữ liệu đúng format backend yêu cầu
  const payload = {
    id: form.value.id,
    name: form.value.name,
    email: form.value.email,
    salary: form.value.salary,
    department: { id: form.value.departmentId }, // Quan trọng: Object lồng nhau
  };

  try {
    if (isEditing.value) {
      // --- UPDATE ---
      await axios.put(`${API_URL}/update`, payload, {
        headers: { Authorization: authHeader },
      });
      alert("Cập nhật thành công!");
    } else {
      // --- ADD ---
      await axios.post(`${API_URL}/add`, payload, {
        headers: { Authorization: authHeader },
      });
      alert("Thêm mới thành công!");
    }
    resetForm();
    fetchEmployees();
  } catch (error) {
    // Hiển thị lỗi từ GlobalExceptionHandler của em
    alert(
      "Lỗi lưu: " + JSON.stringify(error.response?.data?.data || error.message)
    );
  }
};

// --- HÀM PHỤ TRỢ ---
const editEmployee = (emp) => {
  isEditing.value = true;
  form.value = {
    id: emp.id,
    name: emp.name,
    email: emp.email,
    salary: emp.salary,
    departmentId: emp.department ? emp.department.id : 1,
  };
};

const resetForm = () => {
  isEditing.value = false;
  form.value = { id: null, name: "", email: "", salary: 0, departmentId: 1 };
};

// Chạy ngay khi mở web
onMounted(fetchEmployees);
</script>

<style>
/* CSS đơn giản cho dễ nhìn */
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: sans-serif;
}
.card {
  background: #f4f4f4;
  padding: 15px;
  margin-bottom: 20px;
  border-radius: 8px;
}
.form-group {
  margin-bottom: 10px;
}
label {
  display: inline-block;
  width: 100px;
  font-weight: bold;
}
input,
select {
  padding: 5px;
  width: 200px;
}
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}
th,
td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}
th {
  background-color: #42b883;
  color: white;
}
button {
  padding: 5px 10px;
  margin-right: 5px;
  cursor: pointer;
}
.btn-delete {
  background: #ff4d4d;
  color: white;
  border: none;
}
.btn-edit {
  background: #ffc107;
  border: none;
}
.btn-cancel {
  background: #ccc;
}
</style>
