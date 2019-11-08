package tdc.edu.vn.project.Screen;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tdc.edu.vn.project.Adapter.AdapterDanhSachDen;
import tdc.edu.vn.project.Adapter.AdapterQuanLiNguoiBan;
import tdc.edu.vn.project.Adapter.AdapterQuanLiNguoiDung;
import tdc.edu.vn.project.Admin.AdminHome;
import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class ChinhSuaScreen extends AppCompatActivity {
    private ListView lv3;
    AdapterQuanLiNguoiBan ad;
    ImageButton btnBack;
    SearchView searchView;
    ArrayList<NguoiBan> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_li_nguoi_dung);
        setControl();
        setEvent();
    }

    public void setControl(){
        lv3 = (ListView) findViewById(R.id.lvquanli);
        searchView = (SearchView)findViewById(R.id.sreach);
    }

    public void setEvent(){
        khoiTao();
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_NGUOI_BAN.status_data){

                    data = (ArrayList<NguoiBan>) PetShopFireBase.TABLE_YEU_CAU_CHINH_SUA.data;
                    ad = new AdapterQuanLiNguoiBan(ChinhSuaScreen.this,R.layout.item_nguoi_ban,data);
                    lv3.setAdapter(ad);
                }else {
                    handler.postDelayed(this, 1000);
                    //  Toast.makeText(getApplication(), "Không Có dữ liệu", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChinhSuaScreen.this, AdminHome.class);
                startActivity(intent);
            }
        });

    }

    public void khoiTao(){
        btnBack = findViewById(R.id.backadmin);
        //  data.add(new DanhSachDenScreen("adapter","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        // data.add(new DanhSachDenScreen("adapter","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        //data.add(new DanhSachDenScreen("adapter","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        //data.add(new DanhSachDenScreen("adapter","nhan ahihi","nhanahihi@gmail.com","0377878784"));
    }

}
