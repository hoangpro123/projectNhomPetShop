package tdc.edu.vn.project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class DanhMucThuCungActivity extends AppCompatActivity {
    LinearLayout llCho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_thu_cung);
        setControl();
        setEvent();
    }

    private void setControl() {
        llCho =  (LinearLayout) findViewById(R.id.llCho);
    }
    private void setEvent() {
        llCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhMucThuCungActivity.this, DanhSachThuCungActivity.class);
                startActivity(intent);
            }
        });
    }
}
