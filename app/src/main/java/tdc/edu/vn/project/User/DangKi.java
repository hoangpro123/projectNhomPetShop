package tdc.edu.vn.project.User;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.PetShopModel;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class DangKi extends AppCompatActivity {
    EditText ho, ten, mail, sdt, pass, repass,diachi;
    RadioButton gender;
    RadioGroup group;
    Button tao;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangki);

        AnhXa();
        tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DangKi.this,PetShopFireBase.TABLE_NGUOI_MUA.status_data +"" , Toast.LENGTH_SHORT).show();

                if (pass.getText().toString().equals(repass.getText().toString())) {
                    int re = group.getCheckedRadioButtonId();
                    gender = findViewById(re);
                    String Name = "";
                            Name += ho.getText();
                    Name += ten.getText();
                    NguoiMua nm = new NguoiMua(Name, mail.getText().toString(), pass.getText().toString(), sdt.getText().toString(), diachi.getText().toString(), " " ,gender.getText().toString());
                    PetShopFireBase.pushItem(nm, PetShopFireBase.TABLE_NGUOI_MUA);
                    Toast.makeText(DangKi.this,"dktc", Toast.LENGTH_LONG).show();
                }else{
                    String a = "";
                    a += pass.getText().toString();
                    a += repass.getText().toString();
                    Toast.makeText(DangKi.this,a, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void AnhXa(){
            group = findViewById(R.id.gr);
            ho = findViewById(R.id.edHo);
            ten = findViewById(R.id.edTen);
            mail = findViewById(R.id.edMail);
            sdt = findViewById(R.id.edSDT);
            pass = findViewById(R.id.edpass);
            repass = findViewById(R.id.edRepass);
            diachi = findViewById(R.id.edDC);
            tao = findViewById(R.id.btnTao);
    }
}