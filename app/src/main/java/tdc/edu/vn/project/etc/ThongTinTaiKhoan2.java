package tdc.edu.vn.project.etc;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

        setControl();
        khoiTao();
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
    }
}
