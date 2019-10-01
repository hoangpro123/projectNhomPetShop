package tdc.edu.vn.project.Model;

import java.util.ArrayList;

public class NguoiMua extends PetShopModel{
    public static ArrayList<NguoiMua> list_nguoi_mua = new ArrayList<>();
    String name, username, password, phone, address, image;

    public NguoiMua(String name, String username, String password, String phone, String adress, String image) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = adress;
        this.image = image;
    }

    public NguoiMua() {
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
