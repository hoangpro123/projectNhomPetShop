package tdc.edu.vn.project.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import tdc.edu.vn.project.Adapter.AdapterDonHangNguoiBan;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class QuanLyDonHangNguoiBan extends AppCompatActivity{
    String id_nguoi_ban = "nb002";
    private ListView lv1;
    Spinner spn;
    ArrayList<DonHang> data;
    AdapterDonHangNguoiBan adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quanlydonhangnguoiban);
        PetShopFireBase.bus.register(this);
        setControl();
        setEvent();
    }

    public void setControl() {
        lv1 = findViewById(R.id.lv_dh);
        spn = findViewById(R.id.spn);
    }

    public void setEvent() {
        khoiTao();
    }

    public void khoiTao() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_DON_HANG.status_data && PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data) {
                    data = new ArrayList<>();
                    ArrayList<DonHang> listDonHang = (ArrayList<DonHang>) PetShopFireBase.TABLE_DON_HANG.getData();
                    for (DonHang donHang : listDonHang) {
                        SanPham sanPham = (SanPham) PetShopFireBase.findItem(donHang.getId_san_pham(), PetShopFireBase.TABLE_SAN_PHAM);
                        if (sanPham.getId_nguoi_ban().equals(id_nguoi_ban))
                            data.add(donHang);
                    }
                    adapter = new AdapterDonHangNguoiBan(QuanLyDonHangNguoiBan.this, R.layout.listview_donhang_nguoiban, data);
                    lv1.setAdapter(adapter);
                } else handler.postDelayed(this, 1000);

            }
        });
    }

    @Subscribe
    public void onChanged(String table_name) {
        if (table_name.equals(PetShopFireBase.TABLE_DON_HANG.getName()) || table_name.equals(PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.getName())) {
            khoiTao();
        }
    }
}
