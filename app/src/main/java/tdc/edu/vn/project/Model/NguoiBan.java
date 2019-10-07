package tdc.edu.vn.project.Model;

public class NguoiBan extends PetShopModel{
    String name, username, password, phone, address, image, gender, id_hoa_hong;

    public NguoiBan(String name, String username, String password, String phone, String address, String image, String gender, String id_hoa_hong) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.image = image;
        this.gender = gender;
        this.id_hoa_hong = id_hoa_hong;
    }

    public NguoiBan() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId_hoa_hong() {
        return id_hoa_hong;
    }

    public void setId_hoa_hong(String id_hoa_hong) {
        this.id_hoa_hong = id_hoa_hong;
    }
}
