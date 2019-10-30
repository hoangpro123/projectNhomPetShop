package tdc.edu.vn.project.Model;

public class DonHang extends PetShopModel{
    String id_nguoi_mua, id_san_pham;
    Integer so_luong, tinh_trang;
    Double tong_tien;

    public DonHang(String id_nguoi_mua, String id_san_pham, Integer so_luong, Integer tinh_trang, Double tong_tien) {
        this.id_nguoi_mua = id_nguoi_mua;
        this.id_san_pham = id_san_pham;
        this.so_luong = so_luong;
        this.tinh_trang = tinh_trang;
        this.tong_tien = tong_tien;
    }

    public DonHang() {
    }

    public String getId_nguoi_mua() {
        return id_nguoi_mua;
    }

    public void setId_nguoi_mua(String id_nguoi_mua) {
        this.id_nguoi_mua = id_nguoi_mua;
    }

    public String getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(String id_san_pham) {
        this.id_san_pham = id_san_pham;
    }

    public Integer getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(Integer so_luong) {
        this.so_luong = so_luong;
    }

    public Integer getTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(Integer tinh_trang) {
        this.tinh_trang = tinh_trang;
    }

    public Double getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(Double tong_tien) {
        this.tong_tien = tong_tien;
    }
}
