package tdc.edu.vn.project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DanhMucNguoiBanActivity extends AppCompatActivity {
    Button GianHang, ThemSanPham;
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
    }
}
