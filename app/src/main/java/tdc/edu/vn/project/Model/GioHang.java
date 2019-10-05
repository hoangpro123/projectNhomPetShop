package tdc.edu.vn.project.Model;

public class GioHang extends PetShopModel{
    String id_nguoi_mua, id_san_pham;

    public GioHang(String id_nguoi_mua, String id_san_pham) {
        this.id_nguoi_mua = id_nguoi_mua;
        this.id_san_pham = id_san_pham;
    }
    public GioHang() {
    }


    public String getId_nguoi_mua() {
        return id_nguoi_mua;
    }

    public void setId_nguoi_mua(String id_nguoi_mua) {
        this.id_nguoi_mua = id_nguoi_mua;
    }

    public String getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(String id_san_pham) {
        this.id_san_pham = id_san_pham;
    }


}
