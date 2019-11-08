package tdc.edu.vn.project.Admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.project.PetShopSharedPreferences;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.Screen.ChinhSuaScreen;
import tdc.edu.vn.project.Screen.DanhSachDenScreen;
import tdc.edu.vn.project.Screen.Login;
import tdc.edu.vn.project.Screen.NguoiBanScreen;
import tdc.edu.vn.project.Screen.NguoiDungScreen;
import tdc.edu.vn.project.Screen.ThemNguoiBan;

public class AdminHome extends AppCompatActivity {
    Button nguoidung, nguoiban, chinhsua, them, DangXuat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);
        setControl();
        setEvent();
    }

    private void setEvent() {
        nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), NguoiDungScreen.class);
                startActivity(intent);
            }
        });
        nguoiban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), NguoiBanScreen.class);
                startActivity(intent);
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ThemNguoiBan.class);
                startActivity(intent);
            }
        });
        chinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ChinhSuaScreen.class);
                startActivity(intent);
            }
        });

        DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
                sharedPreferences.edit().putString(PetShopSharedPreferences.idql, null).apply();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }

    public void setControl(){
        nguoidung = (Button)findViewById(R.id.btnquanli);
        nguoiban = (Button)findViewById(R.id.btngiaodich);
        chinhsua = (Button)findViewById(R.id.btnchinhsua);
        them = (Button)findViewById(R.id.btnthem);
        DangXuat = findViewById(R.id.btnDangXuat);
    }
}
