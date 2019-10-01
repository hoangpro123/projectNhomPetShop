package tdc.edu.vn.project;

import android.os.Bundle;
import android.os.Handler;

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
        PetShopFireBase.nm.TABLE.child("dg001").setValue(new NguoiMua("nm001", "nm001", "nm001", "nb001", "ndsfs", "5"));
        PetShopFireBase.dg.TABLE.child("dg001").setValue(new DanhGia("nm001", "nb001", "ndsfs", "5"));
        PetShopFireBase.dsd.TABLE.child("dg001").setValue(new DanhSachDen("nm001", "nb001"));
        PetShopFireBase.dh.TABLE.child("dg001").setValue(new DonHang("nm001", "nb001", "ndsfs", "5", "abc", "abc"));
        PetShopFireBase.gh.TABLE.child("dg001").setValue(new GiaoHang("nm001", "11-11-2011"));
        PetShopFireBase.cart.TABLE.child("dg001").setValue(new GioHang("nm001", "nb001"));
        PetShopFireBase.hh.TABLE.child("dg001").setValue(new HoaHong("nm001", "nb001", "ndsfs"));
        PetShopFireBase.nb.TABLE.child("dg001").setValue(new NguoiBan("nm001", "nb001", "ndsfs", "5", "abc", "abc", "abc"));
        PetShopFireBase.ng.TABLE.child("dg001").setValue(new NguoiGiao("nm001", "nb001", "ndsfs"));
        PetShopFireBase.ql.TABLE.child("dg001").setValue(new QuanLy("nm001", "nb001", "ndsfs"));
        PetShopFireBase.sp.TABLE.child("dg001").setValue(new SanPham("nm001", "nb001", "ndsfs", "5", "abc", "abc"));
        //
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.nm.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.dg.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.dsd.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.dh.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.gh.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.cart.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.hh.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.nb.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.ng.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.ql.key).setValue(1);
        PetShopFireBase.TABLE_LAST_ID.child(PetShopFireBase.sp.key).setValue(1);
        //
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.nm.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.dg.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.dsd.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.dh.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.gh.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.cart.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.hh.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.nb.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.ng.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.ql.key).setValue(1);
        PetShopFireBase.TABLE_COUNT.child(PetShopFireBase.sp.key).setValue(1);
    }
}
