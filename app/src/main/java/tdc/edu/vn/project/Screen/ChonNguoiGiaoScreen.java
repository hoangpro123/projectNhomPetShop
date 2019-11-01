package tdc.edu.vn.project.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Date;

import tdc.edu.vn.project.Adapter.AdapterNguoiGiao;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.GiaoHang;
import tdc.edu.vn.project.Model.NguoiGiao;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class ChonNguoiGiaoScreen extends AppCompatActivity {
    String iddh;

    ListView lvDanhSachNguoiGiao;
    ImageButton btnBack;

    AdapterNguoiGiao adapter;
    ArrayList<NguoiGiao> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_nguoi_giao);

        iddh = getIntent().getStringExtra("iddh");
        PetShopFireBase.bus.register(this);
        lvDanhSachNguoiGiao = findViewById(R.id.lvDanhSachNguoiGiao);
        btnBack = findViewById(R.id.btnBackChonNguoiGiao);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        khoiTao();
    }

    private void khoiTao() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_NGUOI_GIAO.status_data) {
                    data = (ArrayList<NguoiGiao>) PetShopFireBase.TABLE_NGUOI_GIAO.data;
                    adapter = new AdapterNguoiGiao(ChonNguoiGiaoScreen.this, R.layout.listview_nguoigiao, data);
                    lvDanhSachNguoiGiao.setAdapter(adapter);
                    lvDanhSachNguoiGiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            DonHang item = (DonHang) PetShopFireBase.findItem(iddh,PetShopFireBase.TABLE_DON_HANG);
                            item.setTinh_trang(2);
                            NguoiGiao nguoiGiao = data.get(i);
                            nguoiGiao.setTinh_trang("B");

                            PetShopFireBase.pushItem(nguoiGiao,PetShopFireBase.TABLE_NGUOI_GIAO);
                            PetShopFireBase.pushItem(item, PetShopFireBase.TABLE_DON_HANG);
                            PetShopFireBase.pushItem(new GiaoHang(nguoiGiao.getId(),iddh,new Date()),PetShopFireBase.TABLE_GIAO_HANG);

                            onBackPressed();
                        }
                    });
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    @Subscribe
    public void onChange(String table_name) {
        if (table_name.equals(PetShopFireBase.TABLE_NGUOI_GIAO.getName())) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PetShopFireBase.bus.unregister(this);
    }
}
