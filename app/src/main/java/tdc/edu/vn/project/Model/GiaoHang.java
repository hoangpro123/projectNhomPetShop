package tdc.edu.vn.project.Model;

import java.util.Date;

public class GiaoHang extends PetShopModel{
    String id_nguoi_giao;
    Date thoi_gian_giao;

    public GiaoHang(String id_nguoi_giao, Date thoi_gian_giao) {
        this.id_nguoi_giao = id_nguoi_giao;
        this.thoi_gian_giao = thoi_gian_giao;
    }

    public GiaoHang() {
    }


    public String getId_nguoi_giao() {
        return id_nguoi_giao;
    }

    public void setId_nguoi_giao(String id_nguoi_giao) {
        this.id_nguoi_giao = id_nguoi_giao;
    }

    public Date getThoi_gian_giao() {
        return thoi_gian_giao;
    }

    public void setThoi_gian_giao(Date thoi_gian_giao) {
        this.thoi_gian_giao = thoi_gian_giao;
    }
}
