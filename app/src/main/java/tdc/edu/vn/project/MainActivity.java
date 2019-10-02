package tdc.edu.vn.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.project.Model.DanhGia;
import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.GiaoHang;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.Model.HoaHong;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiGiao;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.QuanLy;
import tdc.edu.vn.project.Model.SanPham;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    void initial() {
        PetShopFireBase.TABLE_NGUOI_MUA.TABLE.child("dg001").setValue(new NguoiMua("nm001", "nm001", "nm001", "nb001", "ndsfs", "5"));
        PetShopFireBase.TABLE_DANH_GIA.TABLE.child("dg001").setValue(new DanhGia("nm001", "nb001", "ndsfs", "5"));
        PetShopFireBase.TABLE_DANH_SACH_DEN.TABLE.child("dg001").setValue(new DanhSachDen("nm001", "nb001"));
        PetShopFireBase.TABLE_DON_HANG.TABLE.child("dg001").setValue(new DonHang("nm001", "nb001", "ndsfs", "5", "abc", "abc"));
        PetShopFireBase.TABLE_GIAO_HANG.TABLE.child("dg001").setValue(new GiaoHang("nm001", "11-11-2011"));
        PetShopFireBase.TABLE_GIO_HANG.TABLE.child("dg001").setValue(new GioHang("nm001", "nb001"));
        PetShopFireBase.TABLE_HOA_HONG.TABLE.child("dg001").setValue(new HoaHong("nm001", "nb001", "ndsfs"));
        PetShopFireBase.TABLE_NGUOI_BAN.TABLE.child("dg001").setValue(new NguoiBan("nm001", "nb001", "ndsfs", "5", "abc", "abc", "abc"));
        PetShopFireBase.TABLE_NGUOI_GIAO.TABLE.child("dg001").setValue(new NguoiGiao("nm001", "nb001", "ndsfs"));
        PetShopFireBase.TABLE_QUAN_LY.TABLE.child("dg001").setValue(new QuanLy("nm001", "nb001", "ndsfs"));
        PetShopFireBase.TABLE_SAN_PHAM.TABLE.child("dg001").setValue(new SanPham("nm001", "nb001", "ndsfs", "5", "abc", "abc"));
        //
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_NGUOI_MUA.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_DANH_GIA.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_DANH_SACH_DEN.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_DON_HANG.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_GIAO_HANG.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_GIO_HANG.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_HOA_HONG.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_NGUOI_BAN.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_NGUOI_GIAO.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_QUAN_LY.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.TABLE_SAN_PHAM.key).setValue(1);
        //
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_NGUOI_MUA.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_DANH_GIA.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_DANH_SACH_DEN.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_DON_HANG.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_GIAO_HANG.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_GIO_HANG.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_HOA_HONG.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_NGUOI_BAN.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_NGUOI_GIAO.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_QUAN_LY.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.TABLE_SAN_PHAM.key).setValue(1);
    }
}