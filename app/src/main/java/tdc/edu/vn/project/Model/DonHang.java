package tdc.edu.vn.project.Model;

public class DonHang extends PetShopModel{
    String id_nguoi_mua, id_san_pham, id_giao, so_luong, tong_tien, tinh_trang;

    public DonHang(String id_nguoi_mua, String id_san_pham, String id_giao, String so_luong, String tong_tien, String tinh_trang) {
        this.id_nguoi_mua = id_nguoi_mua;
        this.id_san_pham = id_san_pham;
        this.id_giao = id_giao;
        this.so_luong = so_luong;
        this.tong_tien = tong_tien;
        this.tinh_trang = tinh_trang;
    }

    public DonHang() {
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
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

    public String getId_giao() {
        return id_giao;
    }

    public void setId_giao(String id_giao) {
        this.id_giao = id_giao;
    }

    public String getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(String so_luong) {
        this.so_luong = so_luong;
    }

    public String getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(String tong_tien) {
        this.tong_tien = tong_tien;
    }

    public String getTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(String tinh_trang) {
        this.tinh_trang = tinh_trang;
    }
}
