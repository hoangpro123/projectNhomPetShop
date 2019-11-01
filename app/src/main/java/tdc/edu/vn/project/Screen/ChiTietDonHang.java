package tdc.edu.vn.project.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.security.PublicKey;

import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.Model.TinhTrangDonHang;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class ChiTietDonHang extends AppCompatActivity {
    String iddh;

    TextView tvID_DON_HANG,tvTEN_SAN_PHAM,tvTEN_NGUOI_MUA,tvTONG_TIEN,tvTINH_TRANG;

    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);

        PetShopFireBase.bus.register(this);
        iddh = getIntent().getStringExtra("iddh");
        setControl();
        setEvent();
        khoiTao();
    }
    void setEvent(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    void setControl(){
        btnBack = findViewById(R.id.btnBackChiTietDonHang);
        tvID_DON_HANG = findViewById(R.id.tvID_DON_HANG_CTDH);
        tvTEN_SAN_PHAM = findViewById(R.id.tvTEN_SAN_PHAM_CTDH);
        tvTEN_NGUOI_MUA = findViewById(R.id.tvTEN_NGUOI_MUA_CTDH);
        tvTONG_TIEN = findViewById(R.id.tvTONG_TIEN_CTDH);
        tvTINH_TRANG = findViewById(R.id.tvTINH_TRANG_CTDH);
    }

    void khoiTao(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_DON_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data && PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.status_data && PetShopFireBase.TABLE_NGUOI_MUA.status_data){
                    DonHang donHang = (DonHang) PetShopFireBase.findItem(iddh,PetShopFireBase.TABLE_DON_HANG);
                    SanPham sanPham = (SanPham) PetShopFireBase.findItem(donHang.getId_san_pham(),PetShopFireBase.TABLE_SAN_PHAM);
                    NguoiMua nguoiMua = (NguoiMua) PetShopFireBase.findItem(donHang.getId_nguoi_mua(),PetShopFireBase.TABLE_NGUOI_MUA);
                    TinhTrangDonHang tinhTrangDonHang = (TinhTrangDonHang) PetShopFireBase.findItem(String.valueOf(donHang.getTinh_trang()),PetShopFireBase.TABLE_TINH_TRANG_DON_HANG);

                    tvID_DON_HANG.setText(donHang.getId());
                    tvTEN_SAN_PHAM.setText(sanPham.getName());
                    tvTEN_NGUOI_MUA.setText(nguoiMua.getName());
                    tvTONG_TIEN.setText(String.valueOf(donHang.getTong_tien()));
                    tvTINH_TRANG.setText(tinhTrangDonHang.getName());

                }else handler.postDelayed(this,1000);
            }
        });
    }

    @Subscribe
    public void onChange(String table_name){
        if(table_name.equals(PetShopFireBase.TABLE_DON_HANG.getName()) || table_name.equals(PetShopFireBase.TABLE_SAN_PHAM.getName()) || table_name.equals(PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.getName()) || table_name.equals(PetShopFireBase.TABLE_NGUOI_MUA.getName())){
            khoiTao();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PetShopFireBase.bus.unregister(this);
    }
}
