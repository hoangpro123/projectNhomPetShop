package tdc.edu.vn.project.Screen;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import tdc.edu.vn.project.Adapter.AdapterDanhSachDen;
import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class DanhSachDenScreen extends AppCompatActivity {
    private ListView lv3;
    AdapterDanhSachDen ad;
    ImageButton back;
    SearchView searchView;
    ArrayList<DanhSachDen> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_den);
        setControl();
        setEvent();
    }

    public void setControl(){
        back = findViewById(R.id.backdsden);
        lv3 = (ListView) findViewById(R.id.lv3);
        searchView = (SearchView)findViewById(R.id.sreach);
    }

    public void setEvent(){
        khoiTao();
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
                if (PetShopFireBase.TABLE_DANH_SACH_DEN.status_data){

                    data = (ArrayList<DanhSachDen>) PetShopFireBase.TABLE_DANH_SACH_DEN.data;
                     ad = new AdapterDanhSachDen(DanhSachDenScreen.this,R.layout.item_danh_sach_den,data);
                    //Toast.makeText(getApplication(), data.get(0).getId_nguoi_ban(), Toast.LENGTH_SHORT).show();
                    lv3.setAdapter(ad);
                    handler.postDelayed(this, 1000);

                }else {
                    handler.postDelayed(this, 1000);
                  //  Toast.makeText(getApplication(), "Không Có dữ liệu", Toast.LENGTH_SHORT).show();
                }

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ad.filter(newText);
                return false;
            }
        });


    }

    public void khoiTao(){
      //  data.add(new DanhSachDenScreen("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
       // data.add(new DanhSachDenScreen("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        //data.add(new DanhSachDenScreen("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        //data.add(new DanhSachDenScreen("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
    }
}
