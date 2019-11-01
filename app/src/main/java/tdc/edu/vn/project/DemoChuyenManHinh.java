package tdc.edu.vn.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.PetShopModel;

public class DemoChuyenManHinh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_chuyen_man_hinh);

        ((Button) findViewById(R.id.btnChuyen)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DemoChuyenManHinh.this, DonHangActivity.class);
                intent.putExtra("id", "dh002");
                startActivity(intent);
            }
        });

    }
}
