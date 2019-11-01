package tdc.edu.vn.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ThongTinTaiKhoan2 extends AppCompatActivity {
    EditText edtHoTen, edtEmail, edtSDT, edtDiaChi;
    RadioGroup rgGioiTinh;
    RadioButton rbNam, rbNu;
    Button btnYeuCauChinhSua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongtintaikhoan2);

        setControl();

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    private void setControl() {
        edtHoTen = (EditText)findViewById(R.id.edtHoTen);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSDT = (EditText)findViewById(R.id.edtSDT);
        edtDiaChi = (EditText)findViewById(R.id.edtDiaChi);
        rgGioiTinh = (RadioGroup)findViewById(R.id.rgGioiTinh);
        rbNam = (RadioButton)findViewById(R.id.rbNam);
        rbNu = (RadioButton)findViewById(R.id.rbNu);
        btnYeuCauChinhSua = (Button)findViewById(R.id.btnYeuCauChinhSua);
    }
}
