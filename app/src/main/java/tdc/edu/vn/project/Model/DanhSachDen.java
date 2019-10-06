package tdc.edu.vn.project.Model;

public class DanhSachDen extends PetShopModel{
    String id_nguoi_ban, id_nguoi_mua;

    public DanhSachDen(String id_nguoi_ban, String id_nguoi_mua) {
        this.id_nguoi_ban = id_nguoi_ban;
        this.id_nguoi_mua = id_nguoi_mua;

    }

    public DanhSachDen() {
    }


    public String getId_nguoi_ban() {
        return id_nguoi_ban;
    }

    public void setId_nguoi_ban(String id_nguoi_ban) {
        this.id_nguoi_ban = id_nguoi_ban;
    }

    public String getId_nguoi_mua() {
        return id_nguoi_mua;
    }

    public void setId_nguoi_mua(String id_nguoi_mua) {
        this.id_nguoi_mua = id_nguoi_mua;
    }
}
