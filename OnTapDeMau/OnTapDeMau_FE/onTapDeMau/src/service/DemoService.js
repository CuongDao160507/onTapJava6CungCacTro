const baseUrl = "http://localhost:8080/don-hang/getAllDisplay"

export const fetchAllDonHang = async () => {
    const response = await fetch(`${baseUrl}`);
    if (!response.ok) {
        throw new Error("Failed to fetch data");
    }
    return await response.json();
}