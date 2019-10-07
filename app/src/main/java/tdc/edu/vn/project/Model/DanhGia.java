package tdc.edu.vn.project.Model;

public class DanhGia extends PetShopModel {
    String id_nguoi_bi_danh_gia, id_nguoi_danh_gia, content;
    Float rate;

    public DanhGia(String id_nguoi_bi_danh_gia, String id_nguoi_danh_gia, String content, Float rate) {
        this.id_nguoi_bi_danh_gia = id_nguoi_bi_danh_gia;
        this.id_nguoi_danh_gia = id_nguoi_danh_gia;
        this.content = content;
        this.rate = rate;
    }

    public DanhGia() {
    }

    public String getId_nguoi_bi_danh_gia() {
        return id_nguoi_bi_danh_gia;
    }

    public void setId_nguoi_bi_danh_gia(String id_nguoi_bi_danh_gia) {
        this.id_nguoi_bi_danh_gia = id_nguoi_bi_danh_gia;
    }

    public String getId_nguoi_danh_gia() {
        return id_nguoi_danh_gia;
    }

    public void setId_nguoi_danh_gia(String id_nguoi_danh_gia) {
        this.id_nguoi_danh_gia = id_nguoi_danh_gia;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
