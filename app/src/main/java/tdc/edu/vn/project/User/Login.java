package tdc.edu.vn.project.User;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.DatePicker;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import tdc.edu.vn.project.HomeClient;
import tdc.edu.vn.project.Model.DanhGia;
import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.GiaoHang;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.Model.HoaHong;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiGiao;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.PetShopModel;
import tdc.edu.vn.project.Model.QuanLy;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;


public class Login extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap;
    RadioGroup rdbLuaChon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.layout_dangnhap);

        setControl();

        setEvent();
        PetShopFireBase.loadTable(PetShopFireBase.TABLE_NGUOI_MUA);
    }

    private void setEvent() {


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (PetShopFireBase.TABLE_NGUOI_MUA.status_last_id && PetShopFireBase.TABLE_NGUOI_MUA.status_count && PetShopFireBase.TABLE_NGUOI_MUA.status_TABLE) {
                            ArrayList<NguoiMua> data = (ArrayList<NguoiMua>) PetShopFireBase.TABLE_NGUOI_MUA.data;
                            for (int i = 0; i < data.size(); i++) {
                                if (edtTaiKhoan.getText().toString().equals(data.get(i).getUsername()) && edtMatKhau.getText().toString().equals(data.get(i).getPassword())) {
                                    Intent intent = new Intent(Login.this, HomeClient.class);
                                    startActivity(intent);
                                    finish();
                                    return;
                                }
                            }
                            Toast.makeText(Login.this, "Ngu", Toast.LENGTH_SHORT).show();
                        } else handler.postDelayed(this, 1000);
                    }
                });

            }
        });

    }

    private void setControl() {
        edtTaiKhoan = (EditText) findViewById(R.id.edtTaiKhoan);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        rdbLuaChon = (RadioGroup) findViewById(R.id.rdbLuaChon);
    }


}