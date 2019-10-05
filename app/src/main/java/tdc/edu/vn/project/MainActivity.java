package tdc.edu.vn.project;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_dangnhap);

        setControl();

        setEvent();
    }

    private void setEvent() {

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "đăng nhập ahihi", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setControl() {
        edtTaiKhoan = (EditText)findViewById(R.id.edtTaiKhoan);
        edtMatKhau = (EditText)findViewById(R.id.edtMatKhau);
        btnDangNhap = (Button)findViewById(R.id.btnDangNhap);

        setContentView(R.layout.layout_dangki);

    }
}
