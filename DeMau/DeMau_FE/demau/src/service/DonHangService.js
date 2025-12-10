const baseUrl = "http://localhost:8080/don-hang/display"

export const fetchAllDonHang = async () => {
    const response = await fetch(`${baseUrl}`)
    if (!response.ok) {
        throw new Error(response.status + ":" + await response.text())
    }
    return await response.json()
}