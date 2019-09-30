package tdc.edu.vn.project.Model;

public class DanhGia {
    String id,id_nguoi_bi_danh_gia, id_nguoi_danh_gia, comment, rate;

    public DanhGia(String id_nguoi_bi_danh_gia, String id_nguoi_danh_gia, String comment, String rate) {
        this.id_nguoi_bi_danh_gia = id_nguoi_bi_danh_gia;
        this.id_nguoi_danh_gia = id_nguoi_danh_gia;
        this.comment = comment;
        this.rate = rate;
    }

    public DanhGia() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
