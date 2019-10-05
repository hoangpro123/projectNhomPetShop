package tdc.edu.vn.project.Model;

public class NguoiGiao {
    String id, name, phone, tinh_trang;

    public NguoiGiao(String name, String phone, String tinh_trang) {
        this.name = name;
        this.phone = phone;
        this.tinh_trang = tinh_trang;
    }

    public NguoiGiao() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(String tinh_trang) {
        this.tinh_trang = tinh_trang;
    }
}
