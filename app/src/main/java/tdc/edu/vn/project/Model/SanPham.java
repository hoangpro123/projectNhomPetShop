package tdc.edu.vn.project.Model;

public class SanPham {
    String id, name, description, price, image, ngay_dang_ban, id_nguoi_ban;



    public SanPham(String name, String description, String price, String image, String ngay_dang_ban, String id_nguoi_ban) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.ngay_dang_ban = ngay_dang_ban;
        this.id_nguoi_ban = id_nguoi_ban;
    }
    public SanPham() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNgay_dang_ban() {
        return ngay_dang_ban;
    }

    public void setNgay_dang_ban(String ngay_dang_ban) {
        this.ngay_dang_ban = ngay_dang_ban;
    }

    public String getId_nguoi_ban() {
        return id_nguoi_ban;
    }

    public void setId_nguoi_ban(String id_nguoi_ban) {
        this.id_nguoi_ban = id_nguoi_ban;
    }
}
