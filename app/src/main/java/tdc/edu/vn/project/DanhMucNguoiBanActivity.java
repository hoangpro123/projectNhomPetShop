package tdc.edu.vn.project;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Screen.Login;

public class DanhMucNguoiBanActivity extends AppCompatActivity {
    String id;
    Button GianHang, ThemSanPham, DangXuat;
    TextView NameShop;
    NguoiBan nguoiBan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_nguoi_ban);

        setControl();
        setEvent();
    }

    private void setEvent() {
        GianHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhMucNguoiBanActivity.this, DanhSachSanPhamNguoiBanActivity.class);
                intent.putExtra("id_nguoi_ban", id);
                startActivity(intent);
            }
        });
        ThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhMucNguoiBanActivity.this, ThemSanPhamActivity.class);
                intent.putExtra("id_nguoi_ban", id);
                startActivity(intent);
            }
        });
        DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
                sharedPreferences.edit().putString(PetShopSharedPreferences.idnb, null).apply();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }

    private void setControl() {
        GianHang = (Button) findViewById(R.id.btnGianHang);
        ThemSanPham = (Button) findViewById(R.id.btnThemSanPham);
        NameShop = (TextView) findViewById(R.id.tvNameShop);
        DangXuat = (Button) findViewById(R.id.btnDangXuat);
        SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
        id = sharedPreferences.getString(PetShopSharedPreferences.idnb, null);
        getFirebaseSanPham();
    }
    private void getFirebaseSanPham() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_NGUOI_BAN.status_data){
                    nguoiBan = (NguoiBan) PetShopFireBase.findItem(id, PetShopFireBase.TABLE_NGUOI_BAN);
                    NameShop.setText(nguoiBan.getName() + " Shop");
                }
                else handler.postDelayed(this, 1000);
            }
        });

    }
}
