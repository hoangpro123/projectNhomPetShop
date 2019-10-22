package tdc.edu.vn.project.Screen;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import tdc.edu.vn.project.Adapter.AdapterDanhSachDen;
import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class DanhSachDenScreen extends AppCompatActivity {
    private ListView lv3;
    ArrayList<DanhSachDen> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_den);
        setControl();
        setEvent();
    }

    public void setControl(){
        lv3 = (ListView) findViewById(R.id.lv3);
    }

    public void setEvent(){
        khoiTao();
        Toast.makeText(DanhSachDenScreen.this, PetShopFireBase.TABLE_DANH_SACH_DEN.data.toString(), Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_DANH_SACH_DEN.status_data){


                    data = (ArrayList<DanhSachDen>) PetShopFireBase.TABLE_DANH_SACH_DEN.data;
                    AdapterDanhSachDen ad = new AdapterDanhSachDen(DanhSachDenScreen.this,R.layout.item_danh_sach_den,data);
                    Toast.makeText(getApplication(), data.get(0).getId_nguoi_ban(), Toast.LENGTH_SHORT).show();
                    lv3.setAdapter(ad);

                }else {
                    handler.postDelayed(this, 1000);
                    Toast.makeText(getApplication(), "Không Có dữ liệu", Toast.LENGTH_SHORT).show();
                }

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
