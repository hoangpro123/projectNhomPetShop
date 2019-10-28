package tdc.edu.vn.project.Model;

public class TinhTrangDonHang extends PetShopModel{
    String name;

    public TinhTrangDonHang(String name) {
        this.name = name;
    }

    public TinhTrangDonHang() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
