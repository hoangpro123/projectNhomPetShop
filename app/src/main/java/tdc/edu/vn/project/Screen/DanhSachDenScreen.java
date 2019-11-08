package tdc.edu.vn.project.Screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import tdc.edu.vn.project.Adapter.AdapterDanhSachDen;
import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.PetShopSharedPreferences;
import tdc.edu.vn.project.R;

public class DanhSachDenScreen extends AppCompatActivity {
    private ListView lv3;
    AdapterDanhSachDen adapter;
    SearchView searchView;
    Button back;
    ArrayList<DanhSachDen> data;

    String idnb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_den);
        setControl();
        setEvent();
    }

    public void setControl(){
        PetShopFireBase.bus.register(this);
        SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
        idnb = sharedPreferences.getString(PetShopSharedPreferences.idnb,null);

        back = findViewById(R.id.btnBack);
        lv3 = (ListView) findViewById(R.id.lv3);
        searchView = (SearchView)findViewById(R.id.sreach);
    }

    public void setEvent(){
        khoiTao();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });

    }

    public void khoiTao(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_DANH_SACH_DEN.status_data){
                    data = (ArrayList<DanhSachDen>) PetShopFireBase.search("id_nguoi_ban",idnb,PetShopFireBase.TABLE_DANH_SACH_DEN);
                    adapter = new AdapterDanhSachDen(DanhSachDenScreen.this,R.layout.item_danh_sach_den,data);
                    lv3.setAdapter(adapter);
                }else handler.postDelayed(this, 1000);
            }
        });
    }

    @Subscribe
    public void onChanged(String table_name){
        if(table_name.equals(PetShopFireBase.TABLE_DANH_SACH_DEN.getName()) || table_name.equals(PetShopFireBase.TABLE_NGUOI_MUA.getName())){
            khoiTao();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PetShopFireBase.bus.unregister(this);
    }
}
