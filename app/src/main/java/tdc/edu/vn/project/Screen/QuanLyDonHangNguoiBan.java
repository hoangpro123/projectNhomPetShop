package tdc.edu.vn.project.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import tdc.edu.vn.project.Adapter.AdapterDonHangNguoiBan;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.PetShopSharedPreferences;
import tdc.edu.vn.project.R;

public class QuanLyDonHangNguoiBan extends AppCompatActivity{
    String id_nguoi_ban;
    private ListView lv;
    ImageButton Back;
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
        SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
        id_nguoi_ban = sharedPreferences.getString(PetShopSharedPreferences.idnb,null);
        Back = findViewById(R.id.btnBack);
        lv = findViewById(R.id.lv_dh);
        spn = findViewById(R.id.spn);
    }

    public void setEvent() {
        khoiTao();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void khoiTao() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_DON_HANG.status_data && PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data) {
                    data = new ArrayList<>();
                    ArrayList<DonHang> listDonHang = (ArrayList<DonHang>) PetShopFireBase.TABLE_DON_HANG.getData();
                    for (DonHang donHang: listDonHang) {
                        SanPham sanPham = (SanPham) PetShopFireBase.findItem(donHang.getId_san_pham(), PetShopFireBase.TABLE_SAN_PHAM);
                        if (sanPham.getId_nguoi_ban().equals(id_nguoi_ban))
                            data.add(donHang);
                    }
                    adapter = new AdapterDonHangNguoiBan(QuanLyDonHangNguoiBan.this, R.layout.listview_donhang_nguoiban, data);
                    lv.setAdapter(adapter);
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
