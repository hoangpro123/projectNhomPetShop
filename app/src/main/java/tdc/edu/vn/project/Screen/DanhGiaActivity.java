package tdc.edu.vn.project.Screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.project.Adapter.AdapterDonHangNguoiMua;
import tdc.edu.vn.project.Model.DanhGia;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class DanhGiaActivity extends AppCompatActivity {
    RatingBar rating;
    TextView tvRatingNumber, tvMaDonHang, tvTenDonHang, tvGia, tvNguoiBan;
    EditText etContent;
    Button btnGui;

    DonHang dh;
    String idnb, idnm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        setContentView(R.layout.activity_danh_gia);

        setControl();
        setEvent();

    }
    void setEvent(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_DON_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data && PetShopFireBase.TABLE_NGUOI_BAN.status_data){
                    String iddh = getIntent().getStringExtra("iddh");

                    dh = (DonHang) PetShopFireBase.findItem(iddh,PetShopFireBase.TABLE_DON_HANG);
                    SanPham sp = (SanPham) PetShopFireBase.findItem(dh.getId_san_pham(),PetShopFireBase.TABLE_SAN_PHAM);
                    NguoiBan nb = (NguoiBan) PetShopFireBase.findItem(sp.getId_nguoi_ban(),PetShopFireBase.TABLE_NGUOI_BAN);

                    idnb = nb.getId();
                    idnm = dh.getId_nguoi_mua();

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
                PetShopFireBase.pushItem(new DanhGia(idnb,idnm,etContent.getText().toString(),rating.getRating()),PetShopFireBase.TABLE_DANH_GIA);
                dh.setTinh_trang(4);
                PetShopFireBase.pushItem(dh,PetShopFireBase.TABLE_DON_HANG);

                Intent intent = new Intent(getApplicationContext(), ThongTinUser.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
    void setControl(){
        rating = findViewById(R.id.rbRating);
        tvRatingNumber = findViewById(R.id.tvRatingNumber);
        tvRatingNumber.setText(String.valueOf(rating.getRating()));
        tvMaDonHang = findViewById(R.id.tvMaDonHang);
        tvTenDonHang = findViewById(R.id.tvTenDonHang);
        tvGia = findViewById(R.id.tvGia);
        tvNguoiBan = findViewById(R.id.tvNguoiBan);
        etContent = findViewById(R.id.etContent);
        btnGui = findViewById(R.id.btnGui);

    }
}
