package tdc.edu.vn.project;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tdc.edu.vn.project.Adapter.RecyclerViewAdapter;
import tdc.edu.vn.project.Model.SanPham;

public class DanhSachSanPhamNguoiBanActivity extends AppCompatActivity {

    RecyclerViewAdapter myAdapter;
    ArrayList<SanPham> data;
    Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham_nguoi_ban);


        getFirebaseSanPham();
        setControl();
        setEvent();
    }
    private void setEvent() {

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachSanPhamNguoiBanActivity.this, DanhMucNguoiBanActivity.class);
                startActivity(intent);
            }
        });
    }



    private void setControl() {

        Back = (Button) findViewById(R.id.btnBack);

    }

    private void getFirebaseSanPham() {

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_SAN_PHAM.status_last_id  && PetShopFireBase.TABLE_SAN_PHAM.status_data){
                    data = (ArrayList<SanPham>)PetShopFireBase.TABLE_SAN_PHAM.data;
                    RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview);
                    myAdapter = new RecyclerViewAdapter(DanhSachSanPhamNguoiBanActivity.this,data);
                    myrv.setLayoutManager(new GridLayoutManager(DanhSachSanPhamNguoiBanActivity.this,2));
                    myrv.setAdapter(myAdapter);
                }
                else handler.postDelayed(this, 1000);
            }
        });

    }
    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
