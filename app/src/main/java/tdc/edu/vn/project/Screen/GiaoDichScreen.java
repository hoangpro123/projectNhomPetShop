package tdc.edu.vn.project.Screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import tdc.edu.vn.project.Adapter.AdapterGiaoDich;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.HoaHong;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class GiaoDichScreen extends AppCompatActivity {
    ListView listView;
    AdapterGiaoDich adapterGiaoDich;
    ArrayList<HoaHong> hoaHongs;
    ArrayList<DonHang> donHangs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_giao_dich);
        listView = (ListView)findViewById(R.id.lvhoahong);
        setEvent();
    }

    private void setEvent() {
        Intent intent = getIntent();
        intent.getStringArrayExtra("id");
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_HOA_HONG.status_data){


                }
            }
        });

    }
}
