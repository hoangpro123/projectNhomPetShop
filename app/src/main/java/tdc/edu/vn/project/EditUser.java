package tdc.edu.vn.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;


public class EditUser extends AppCompatActivity {
    ImageButton hoten,email,sdt,diachi,gioitinh;
    EditText edHoTen,edEmail, edSDT, edDiaChi;
    RadioButton rdNam, rdNu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_nguoi_dung);
        AnhXa();
    }
    public void AnhXa(){

        hoten = findViewById(R.id.btnHoTen);
        email = findViewById(R.id.btnEmail);
        sdt = findViewById(R.id.btnsdt);
        diachi = findViewById(R.id.btnDiaChi);
        gioitinh = findViewById(R.id.btnGioiTinh);

        edHoTen = findViewById(R.id.edhoten);
        edEmail = findViewById(R.id.edemail);
        edSDT = findViewById(R.id.edsdt);
        edDiaChi = findViewById(R.id.edDiaChi);

        rdNam = findViewById(R.id.rdNam);
        rdNu = findViewById(R.id.rdNu);
    }
    public void setEvent(){
        hoten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edHoTen.setEnabled(true);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edEmail.setEnabled(true);
            }
        });
        sdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edSDT.setEnabled(true);
            }
        });
        diachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edDiaChi.setEnabled(true);
            }
        });
        gioitinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdNam.setEnabled(true);
                rdNu.setEnabled(true);
            }
        });
    }
}

