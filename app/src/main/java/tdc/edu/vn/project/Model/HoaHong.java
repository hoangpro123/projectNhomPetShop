package tdc.edu.vn.project.Model;

public class HoaHong {
    String id, ty_le, thoi_gian_dong_tien, so_tien;

    public HoaHong(String ty_le, String thoi_gian_dong_tien, String so_tien) {
        this.ty_le = ty_le;
        this.thoi_gian_dong_tien = thoi_gian_dong_tien;
        this.so_tien = so_tien;
    }

    public HoaHong() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTy_le() {
        return ty_le;
    }

    public void setTy_le(String ty_le) {
        this.ty_le = ty_le;
    }

    public String getThoi_gian_dong_tien() {
        return thoi_gian_dong_tien;
    }

    public void setThoi_gian_dong_tien(String thoi_gian_dong_tien) {
        this.thoi_gian_dong_tien = thoi_gian_dong_tien;
    }

    public String getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(String so_tien) {
        this.so_tien = so_tien;
    }
}
