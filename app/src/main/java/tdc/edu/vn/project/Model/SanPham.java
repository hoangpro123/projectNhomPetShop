package tdc.edu.vn.project.Model;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;

public class SanPham extends PetShopModel{
    String name, description, id_nguoi_ban;
    ArrayList<String> images_list;
    Double price;
    Date ngay_dang_ban;


    public SanPham(String name, String description, ArrayList<String> images_list, String id_nguoi_ban, Double price, Date ngay_dang_ban) {
        this.name = name;
        this.description = description;
        this.id_nguoi_ban = id_nguoi_ban;
        this.images_list = images_list;
        this.price = price;
        this.ngay_dang_ban = ngay_dang_ban;
    }

    public SanPham() {
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

    public String getId_nguoi_ban() {
        return id_nguoi_ban;
    }

    public void setId_nguoi_ban(String id_nguoi_ban) {
        this.id_nguoi_ban = id_nguoi_ban;
    }

    public ArrayList<String> getImages_list() {
        return images_list;
    }

    public void setImages_list(ArrayList<String> images_list) {
        this.images_list = images_list;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getNgay_dang_ban() {
        return ngay_dang_ban;
    }

    public void setNgay_dang_ban(Date ngay_dang_ban) {
        this.ngay_dang_ban = ngay_dang_ban;
    }
}

