package tdc.edu.vn.project.Model;

public class DanhGia extends PetShopModel{
    String id_nguoi_bi_danh_gia, id_nguoi_danh_gia, content, rate;

    public DanhGia(String id_nguoi_bi_danh_gia, String id_nguoi_danh_gia, String content, String rate) {
        this.id_nguoi_bi_danh_gia = id_nguoi_bi_danh_gia;
        this.id_nguoi_danh_gia = id_nguoi_danh_gia;
        this.content = content;
        this.rate = rate;
    }

    public DanhGia() {
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
