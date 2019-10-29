package tdc.edu.vn.project.Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.project.R;

public class AdminHome extends AppCompatActivity {
    Button nguoidung, nguoiban, chinhsua, them;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);
        setControl();
        setEvent();
    }

    private void setEvent() {
        nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), NguoiDungScreen.class);
                startActivity(intent);
            }
        });
        nguoiban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), NguoiBanScreen.class);
                startActivity(intent);
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ThemNguoiBan.class);
                startActivity(intent);
            }
        });
    }

    public void setControl(){
        nguoidung = (Button)findViewById(R.id.btnquanli);
        nguoiban = (Button)findViewById(R.id.btngiaodich);
        chinhsua = (Button)findViewById(R.id.btnchinhsua);
        them = (Button)findViewById(R.id.btnthem);
    }
}
