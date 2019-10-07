package tdc.edu.vn.project.User;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class DangKi  extends AppCompatActivity {
    EditText ho, ten, mail, sdt, pass, repass,diachi;
    RadioButton gender;
    RadioGroup group;
    Button tao;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangki);
        AnhXa();

        PetShopFireBase.loadTable(PetShopFireBase.TABLE_NGUOI_MUA);
        PetShopFireBase.TABLE_NGUOI_MUA.name.toString();
        tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ho.getText().toString() != null && ten.getText().toString() != null && mail.getText().toString() != null && pass.getText().toString() != null && repass.getText().toString() != null && sdt.getText().toString() != null && diachi.getText().toString() != null) {
                    if (pass.getText().toString().equals(repass.getText().toString())) {
                        if (sdt.length() == 10) {
                            int re = group.getCheckedRadioButtonId();
                            gender = findViewById(re);
                            String Name = "";
                            Name += ho.getText();
                            Name += ten.getText();
                            NguoiMua nm = new NguoiMua(Name, mail.getText().toString(), pass.getText().toString(), sdt.getText().toString(), diachi.getText().toString(), " ", gender.getText().toString());
                            PetShopFireBase.pushItem(nm, PetShopFireBase.TABLE_NGUOI_MUA);
                            dialog();
                            Toast.makeText(DangKi.this, "dktc", Toast.LENGTH_LONG).show();
                        } else {
                            dialogfail();
                        }
                    } else {
                        String a = "";
                        a += pass.getText().toString();
                        a += repass.getText().toString();

                        //Toast.makeText(DangKi.this,a, Toast.LENGTH_LONG).show();
                    }
                }else {
                    dialognull();

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
    private void dialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        Button XacNhan = (Button)dialog.findViewById(R.id.xacnhan);
        XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKi.this, Login.class);
                intent.putExtra("user", mail.getText().toString());
                intent.putExtra("pass", pass.getText().toString());
                startActivity(intent);
            }
        });
        dialog.show();
    }
    private void dialogfail(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        Button XacNhan = (Button)dialog.findViewById(R.id.xacnhan);
        final TextView view = (TextView)dialog.findViewById(R.id.tt);
        view.setText("SDT phải có 10 kí tự và bắt đầu bằng số 0");
        XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dialog.cancel();
            }
        });

        dialog.show();
    }
    private void dialognull(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        Button XacNhan = (Button)dialog.findViewById(R.id.xacnhan);
        final TextView view = (TextView)dialog.findViewById(R.id.tt);
        view.setText("Phải Nhập hết Các ô");
        XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
    public boolean checkPhone()
    {
       String num = "";
                   if (sdt.getText().toString().charAt(0) == 0){
                       return true;
                   }else {
                       return false;
                   }
    }
}

