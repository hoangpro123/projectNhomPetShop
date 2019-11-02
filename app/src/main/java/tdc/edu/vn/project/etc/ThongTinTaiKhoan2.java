package tdc.edu.vn.project.etc;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.otto.Subscribe;

import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class ThongTinTaiKhoan2 extends AppCompatActivity {
    private EditText edtHoTen, edtEmail, edtSDT, edtDiaChi;
    private String idnb = "nb001";
    private RadioGroup rgGioiTinh;
    private RadioButton rbNam, rbNu;
    private Button btnYeuCauChinhSua;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongtintaikhoan2);

        PetShopFireBase.bus.register(this);
        setControl();
        khoiTao();
        setEvent();

    }
    private void setEvent(){
        btnYeuCauChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NguoiBan nguoiBan = (NguoiBan) PetShopFireBase.findItem(idnb,PetShopFireBase.TABLE_NGUOI_BAN);
                nguoiBan.setName(edtHoTen.getText().toString());
                nguoiBan.setUsername(edtEmail.getText().toString());
                nguoiBan.setPhone(edtSDT.getText().toString());
                nguoiBan.setAddress(edtDiaChi.getText().toString());

                RadioButton rdoSelectedGender = findViewById(rgGioiTinh.getCheckedRadioButtonId());
                nguoiBan.setGender(rdoSelectedGender.getText().toString());
                PetShopFireBase.pushItem(nguoiBan,PetShopFireBase.TABLE_YEU_CAU_CHINH_SUA);

                Toast.makeText(ThongTinTaiKhoan2.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void khoiTao() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_NGUOI_BAN.status_data){
                    NguoiBan nguoiBan = (NguoiBan) PetShopFireBase.findItem(idnb,PetShopFireBase.TABLE_NGUOI_BAN);
                    edtHoTen.setText(nguoiBan.getName());
                    edtEmail.setText(nguoiBan.getUsername());
                    edtSDT.setText(nguoiBan.getPhone());
                    edtDiaChi.setText(nguoiBan.getAddress());
                    if(nguoiBan.getGender().equals("Nam")){
                        rgGioiTinh.check(R.id.rbNam);
                    }else rgGioiTinh.check(R.id.rbNu);
                }else handler.postDelayed(this, 1000);
            }
        });
    }

    private void setControl() {
        edtHoTen = (EditText)findViewById(R.id.edtHoTen);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSDT = (EditText)findViewById(R.id.edtSDT);
        edtDiaChi= (EditText)findViewById(R.id.edtDiaChi);
        rgGioiTinh = (RadioGroup)findViewById(R.id.rgGioiTinh);
        rbNam = (RadioButton)findViewById(R.id.rbNam);
        rbNu = (RadioButton)findViewById(R.id.rbNu);

        btnYeuCauChinhSua = findViewById(R.id.btnYeuCauChinhSua);
    }

    @Subscribe
    public void onChange(String table_name){
        if(table_name.equals(PetShopFireBase.TABLE_NGUOI_BAN.getName())){
            khoiTao();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PetShopFireBase.bus.unregister(this);
    }
}
