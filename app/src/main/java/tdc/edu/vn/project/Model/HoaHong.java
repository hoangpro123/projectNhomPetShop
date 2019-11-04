package tdc.edu.vn.project.Model;

import java.util.Date;

public class HoaHong extends PetShopModel{
    Float ty_le;
    Date thoi_gian_dong_tien;
    Double so_tien;
    String id_don_hang, tinh_trang_hoa_hong;

    public HoaHong(Float ty_le, Date thoi_gian_dong_tien, Double so_tien, String id_don_hang, String tinh_trang_hoa_hong) {
        this.ty_le = ty_le;
        this.thoi_gian_dong_tien = thoi_gian_dong_tien;
        this.so_tien = so_tien;
        this.id_don_hang = id_don_hang;
        this.tinh_trang_hoa_hong = tinh_trang_hoa_hong;
    }

    public HoaHong() {
    }

    public Float getTy_le() {
        return ty_le;
    }

    public void setTy_le(Float ty_le) {
        this.ty_le = ty_le;
    }

    public Date getThoi_gian_dong_tien() {
        return thoi_gian_dong_tien;
    }

    public void setThoi_gian_dong_tien(Date thoi_gian_dong_tien) {
        this.thoi_gian_dong_tien = thoi_gian_dong_tien;
    }

    public Double getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(Double so_tien) {
        this.so_tien = so_tien;
    }

    public String getId_don_hang() {
        return id_don_hang;
    }

    public void setId_don_hang(String id_don_hang) {
        this.id_don_hang = id_don_hang;
    }

    public String getTinh_trang_hoa_hong() {
        return tinh_trang_hoa_hong;
    }

    public void setTinh_trang_hoa_hong(String tinh_trang_hoa_hong) {
        this.tinh_trang_hoa_hong = tinh_trang_hoa_hong;
    }
}
