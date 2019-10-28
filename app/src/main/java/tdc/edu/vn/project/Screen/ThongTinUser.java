package tdc.edu.vn.project.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import tdc.edu.vn.project.Adapter.AdapterDonHangNguoiMua;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.HoaHong;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;


import com.squareup.otto.Subscribe;


public class ThongTinUser extends AppCompatActivity {
    static String idnm = "nm002";
    private ListView lv1;
    AdapterDonHangNguoiMua adapter;
    ArrayList<DonHang> data;

    TextView tvName, tvEmail, tvSDT;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_thongtinuser);

        PetShopFireBase.bus.register(this);
        setControl();
        setEvent();

    }

    public void setControl() {
        lv1 = findViewById(R.id.lv1);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvSDT = findViewById(R.id.tvSDT);
        img = findViewById(R.id.img);
    }

    public void setEvent() {
        khoiTao();
    }

    public void khoiTao() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_NGUOI_MUA.status_data && PetShopFireBase.TABLE_DON_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data && PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.status_data) {
                    NguoiMua nguoiMua = (NguoiMua) PetShopFireBase.findItem(idnm,PetShopFireBase.TABLE_NGUOI_MUA);
                    tvName.setText(nguoiMua.getName());
                    tvEmail.setText(nguoiMua.getUsername());
                    tvSDT.setText(nguoiMua.getPhone());
                    //
                    data = (ArrayList<DonHang>) PetShopFireBase.search("id_nguoi_mua",idnm,PetShopFireBase.TABLE_DON_HANG);
                    adapter = new AdapterDonHangNguoiMua(ThongTinUser.this, R.layout.listview_donhang_trangthai, data);
                    lv1.setAdapter(adapter);
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    @Subscribe
    public void onChanged(String table_name) {
        if (table_name.equals(PetShopFireBase.TABLE_NGUOI_MUA.getName()) || table_name.equals(PetShopFireBase.TABLE_DON_HANG.getName()) || table_name.equals(PetShopFireBase.TABLE_SAN_PHAM.getName()) || table_name.equals(PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.getName())) {
            khoiTao();
        }
    }
}