package tdc.edu.vn.project.Screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

import tdc.edu.vn.project.DanhMucNguoiBanActivity;
import tdc.edu.vn.project.Admin.AdminHome;
import tdc.edu.vn.project.FragmentMainActivity;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.QuanLy;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.PetShopSharedPreferences;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.etc.QuenMatKhau;


public class Login extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatKhau;
    TextView tvTaoTaiKhoan, tvQuenMatKhau;
    Button btnDangNhap;
    RadioGroup rdbLuaChon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        setControl();
        setEvent();
    }

    public  void setEvent() {
        final Handler handler = new Handler();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDangNhap.setClickable(false);
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        switch (rdbLuaChon.getCheckedRadioButtonId()) {
                            case R.id.rdbNguoiMua:
                                btnDangNhap.setClickable(false);
                                if (PetShopFireBase.TABLE_NGUOI_MUA.status_data) {
                                    ArrayList<NguoiMua> data = (ArrayList<NguoiMua>) PetShopFireBase.TABLE_NGUOI_MUA.data;
                                    for (int i = 0; i < data.size(); i++) {
                                        if (edtTaiKhoan.getText().toString().equals(data.get(i).getUsername()) && edtMatKhau.getText().toString().equals(data.get(i).getPassword())) {
                                            SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString(PetShopSharedPreferences.idnm, data.get(i).getId()).apply();


                                            Intent intent = new Intent(getApplication(), FragmentMainActivity.class);
                                            startActivity(intent);
                                            finish();
                                            return;
                                        }
                                    }
                                    btnDangNhap.setClickable(true);
                                    Toast.makeText(Login.this, "Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                                } else handler.postDelayed(this, 1000);
                                break;
                            case R.id.rdbNguoiBan:

                                if (PetShopFireBase.TABLE_NGUOI_BAN.status_data) {
                                    ArrayList<NguoiBan> data = (ArrayList<NguoiBan>) PetShopFireBase.TABLE_NGUOI_BAN.data;
                                    for (int i = 0; i < data.size(); i++) {
                                        if (edtTaiKhoan.getText().toString().equals(data.get(i).getUsername()) && edtMatKhau.getText().toString().equals(data.get(i).getPassword())) {
                                            SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString(PetShopSharedPreferences.idnb, data.get(i).getId()).apply();


                                            Intent intentNguoiBan = new Intent(getApplication(), DanhMucNguoiBanActivity.class);
                                            startActivity(intentNguoiBan);
                                            finish();
                                            return;
                                        }
                                    }
                                    btnDangNhap.setClickable(true);
                                    Toast.makeText(Login.this, "Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                                } else handler.postDelayed(this, 1000);

                                break;
                            case R.id.rdbQuanLy:
                                btnDangNhap.setClickable(false);

                                if (PetShopFireBase.TABLE_QUAN_LY.status_data) {
                                    ArrayList<QuanLy> data = (ArrayList<QuanLy>) PetShopFireBase.TABLE_QUAN_LY.data;
                                    for (int i = 0; i < data.size(); i++) {
                                        if (edtTaiKhoan.getText().toString().equals(data.get(i).getUsername()) && edtMatKhau.getText().toString().equals(data.get(i).getPassword())) {
                                            SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString(PetShopSharedPreferences.idql, data.get(i).getId()).apply();

                                            Intent intent = new Intent(getApplication(), AdminHome.class);
                                            startActivity(intent);
                                            finish();
                                            return;
                                        }
                                    }
                                    btnDangNhap.setClickable(true);
                                    Toast.makeText(Login.this, "Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                                } else handler.postDelayed(this, 1000);
                                break;
                        }
                    }
                });

            }
        });

        tvTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, DangKi.class));
            }
        });

        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, QuenMatKhau.class));
            }
        });
    }

    private void setControl() {
        edtTaiKhoan = (EditText) findViewById(R.id.edtTaiKhoan);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        rdbLuaChon = (RadioGroup) findViewById(R.id.rdbLuaChon);
        tvTaoTaiKhoan = findViewById(R.id.txtTaoTaiKhoan);
        tvQuenMatKhau = findViewById(R.id.txtQuenMatKhau);
    }


}