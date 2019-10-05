package tdc.edu.vn.project.Model;

import java.util.Date;

public class HoaHong extends PetShopModel{
    Float ty_le;
    Date thoi_gian_dong_tien;
    Double so_tien;

    public HoaHong(Float ty_le, Date thoi_gian_dong_tien, Double so_tien) {
        this.ty_le = ty_le;
        this.thoi_gian_dong_tien = thoi_gian_dong_tien;
        this.so_tien = so_tien;
    }

    public HoaHong(double v, Date thoi_gian_dong_tien, int i) {
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
}
