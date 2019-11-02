package tdc.edu.vn.project.Model;

import java.util.Date;

public class HoaHong extends PetShopModel{
    Float ty_le;
    Date thoi_gian_dong_tien;
    Double so_tien;
    String id_don_hang;

    public HoaHong(Float ty_le, Date thoi_gian_dong_tien, Double so_tien, String id_don_hang) {
        this.ty_le = ty_le;
        this.thoi_gian_dong_tien = thoi_gian_dong_tien;
        this.so_tien = so_tien;
        this.id_don_hang = id_don_hang;
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
}
