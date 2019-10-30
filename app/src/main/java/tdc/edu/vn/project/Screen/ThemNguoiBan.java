package tdc.edu.vn.project.Screen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class ThemNguoiBan extends AppCompatActivity {
    EditText ho, ten, mail, sdt, pass, repass,diachi;
    RadioButton gender;
    ImageButton btnback;
    RadioGroup group;
    Button tao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_nguoi_ban);
        AnhXa();
        SetEvent();
    }

    public void SetEvent(){
        tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass.getText().toString().equals(repass.getText().toString())){
                    int re = group.getCheckedRadioButtonId();
                    gender = findViewById(re);
                    NguoiBan nguoiBan = new NguoiBan(ho.getText().toString(),mail.getText().toString(),pass.getText().toString(),sdt.getText().toString(),diachi.getText().toString(),"",gender.getText().toString(),"");
                    PetShopFireBase.pushItem(nguoiBan, PetShopFireBase.TABLE_NGUOI_BAN);
                    Toast.makeText(ThemNguoiBan.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    public void AnhXa(){


        group = findViewById(R.id.grNgBan);

        ho = findViewById(R.id.edHNgBano);
        mail = findViewById(R.id.edMailNguoiBan);
        sdt = findViewById(R.id.edSDTNguoiBan);
        pass = findViewById(R.id.edPassNguoiBan);
        repass = findViewById(R.id.edRepassNgBan);
        diachi = findViewById(R.id.edDCNgBan);


        btnback = findViewById(R.id.btnBack);
        tao = findViewById(R.id.btnTaoNgBan);

    }
}
