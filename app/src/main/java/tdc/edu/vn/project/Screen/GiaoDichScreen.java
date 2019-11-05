package tdc.edu.vn.project.Screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import tdc.edu.vn.project.Adapter.AdapterGiaoDich;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.HoaHong;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class GiaoDichScreen extends AppCompatActivity {
    ListView listView;
    AdapterGiaoDich adapterGiaoDich;
    ArrayList<HoaHong> hoaHongs;
    String  idnb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_giao_dich);
        listView = (ListView)findViewById(R.id.lvhoahong);
        setEvent();
    }

    private void setEvent() {
        Intent intent = getIntent();
        idnb = intent.getStringExtra("id");

        khoiTao();
    }
    void khoiTao(){
        hoaHongs = new ArrayList<>();
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_HOA_HONG.status_data && PetShopFireBase.TABLE_DON_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data && PetShopFireBase.TABLE_NGUOI_BAN.status_data){
                    ArrayList<HoaHong> list_hoa_hong =(ArrayList<HoaHong>) PetShopFireBase.TABLE_HOA_HONG.data;
                    for(HoaHong hoaHong: list_hoa_hong){
                        DonHang dh = (DonHang) PetShopFireBase.findItem(hoaHong.getId_don_hang(),PetShopFireBase.TABLE_DON_HANG);
                        SanPham sp = (SanPham) PetShopFireBase.findItem(dh.getId_san_pham(),PetShopFireBase.TABLE_SAN_PHAM);
                        NguoiBan nb = (NguoiBan) PetShopFireBase.findItem(sp.getId_nguoi_ban(),PetShopFireBase.TABLE_NGUOI_BAN);

                        if(nb.getId().equals(idnb)){
                            hoaHongs.add(hoaHong);
                        }
                    }
                    Toast.makeText(GiaoDichScreen.this, hoaHongs.size()+"", Toast.LENGTH_SHORT).show();
                    adapterGiaoDich = new AdapterGiaoDich(GiaoDichScreen.this,R.layout.item_quan_ly_don_hang,hoaHongs);
                    listView.setAdapter(adapterGiaoDich);
                }
            }
        });
    }
}
