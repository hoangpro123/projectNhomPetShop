package tdc.edu.vn.project.Screen;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
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
import tdc.edu.vn.project.Adapter.AdapterQuanLiNguoiDung;
import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class NguoiDungScreen extends AppCompatActivity {
    private ListView lv3;
    AdapterQuanLiNguoiDung ad;
    SearchView searchView;
    ArrayList<NguoiMua> data;
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
                if (PetShopFireBase.TABLE_NGUOI_MUA.status_data){

                    data = (ArrayList<NguoiMua>) PetShopFireBase.TABLE_NGUOI_MUA.data;
                    ad = new AdapterQuanLiNguoiDung(NguoiDungScreen.this,R.layout.item_nguoi_dung,data);
                    lv3.setAdapter(ad);
                }else {
                    handler.postDelayed(this, 1000);
                    //  Toast.makeText(getApplication(), "Không Có dữ liệu", Toast.LENGTH_SHORT).show();
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
