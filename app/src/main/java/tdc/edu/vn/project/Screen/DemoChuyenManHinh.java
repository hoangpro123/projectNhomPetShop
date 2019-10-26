package tdc.edu.vn.project.Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.project.R;

public class DemoChuyenManHinh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_chuyen_man_hinh);

        ((Button) findViewById(R.id.btnChuyen)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DemoChuyenManHinh.this, DanhGiaActivity.class);
                intent.putExtra("id", "dh002");
                startActivity(intent);
            }
        });

    }
}
