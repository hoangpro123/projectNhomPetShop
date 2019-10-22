package tdc.edu.vn.project;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.PetShopModel;
import tdc.edu.vn.project.Model.SanPham;

public class DonHangActivity extends AppCompatActivity {
    RatingBar rating;
    TextView tvRatingNumber, tvMaDonHang, tvTenDonHang, tvGia, tvNguoiBan;
    EditText etContent;
    Button btnGui;

    String iddh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        setContentView(R.layout.activity_don_hang);

        setControl();
        setEvent();
    }
    void setEvent(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_DON_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data && PetShopFireBase.TABLE_NGUOI_BAN.status_data){
                    iddh = getIntent().getStringExtra("id");
                    DonHang dh = (DonHang) PetShopFireBase.findItem(iddh,PetShopFireBase.TABLE_DON_HANG);
                    SanPham sp = (SanPham) PetShopFireBase.findItem(dh.getId_san_pham(),PetShopFireBase.TABLE_SAN_PHAM);
                    NguoiBan nb = (NguoiBan) PetShopFireBase.findItem(sp.getId_nguoi_ban(),PetShopFireBase.TABLE_NGUOI_BAN);

                    tvMaDonHang.setText(dh.getId());
                    tvTenDonHang.setText(sp.getName());
                    tvGia.setText(sp.getPrice() + " đ");
                    tvNguoiBan.setText(nb.getName());
                }
                else handler.postDelayed(this,1000);
            }
        });

        //
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tvRatingNumber.setText(String.valueOf(v));
            }

        });
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    void setControl(){
        rating = findViewById(R.id.rbRating);
        tvRatingNumber = findViewById(R.id.tvRatingNumber);
        tvMaDonHang = findViewById(R.id.tvMaDonHang);
        tvTenDonHang = findViewById(R.id.tvTenDonHang);
        tvGia = findViewById(R.id.tvGia);
        tvNguoiBan = findViewById(R.id.tvNguoiBan);
        etContent = findViewById(R.id.etContent);
        btnGui = findViewById(R.id.btnGui);

    }
}
