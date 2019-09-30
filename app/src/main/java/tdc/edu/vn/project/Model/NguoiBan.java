package tdc.edu.vn.project.Model;

public class NguoiBan {
    String id, name, username, password, phone, adress, image, id_hoa_hong;

    public NguoiBan(String name, String username, String password, String phone, String adress, String image, String id_hoa_hong) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.adress = adress;
        this.image = image;
        this.id_hoa_hong = id_hoa_hong;
    }

    public NguoiBan() {
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId_hoa_hong() {
        return id_hoa_hong;
    }

    public void setId_hoa_hong(String id_hoa_hong) {
        this.id_hoa_hong = id_hoa_hong;
    }
}
