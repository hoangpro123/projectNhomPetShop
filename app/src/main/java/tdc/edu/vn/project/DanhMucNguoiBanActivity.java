package tdc.edu.vn.project;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Model.NguoiBan;

public class DanhMucNguoiBanActivity extends AppCompatActivity {
    String id = "null";
    Button GianHang, ThemSanPham;
    TextView NameShop;
    ArrayList<NguoiBan> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_nguoi_ban);
        getFirebaseSanPham();
        setControl();
        setEvent();
    }

    private void setEvent() {
        GianHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhMucNguoiBanActivity.this, DanhSachSanPhamNguoiBanActivity.class);
                startActivity(intent);
            }
        });
        ThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhMucNguoiBanActivity.this, ThemSanPhamActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        GianHang = (Button) findViewById(R.id.btnGianHang);
        ThemSanPham = (Button) findViewById(R.id.btnThemSanPham);
        NameShop = (TextView) findViewById(R.id.tvNameShop);
    }
    private void getFirebaseSanPham() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_NGUOI_BAN.status_data){
                    data = (ArrayList<NguoiBan>) PetShopFireBase.search("id", id, PetShopFireBase.TABLE_NGUOI_BAN);
                    NameShop.setText(data.get(0).getName() + " Shop");
                }
                else handler.postDelayed(this, 1000);
            }
        });

    }
}
