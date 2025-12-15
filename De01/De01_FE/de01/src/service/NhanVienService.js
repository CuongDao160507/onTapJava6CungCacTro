export const fetchAllNhanVien = async () => {
    const respone = await fetch("http://localhost:8080/nhan-vien/getAllDisplay")
    if (!respone.ok) {
        throw new Error(respone.status + ":" + await respone.text())
    }
    return await respone.json();
}