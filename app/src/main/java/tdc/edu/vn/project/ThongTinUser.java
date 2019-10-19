package tdc.edu.vn.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.PetShopModel;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.Model.TinhTrangDonHang;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ThongTinUser extends AppCompatActivity {
    static String id = "nm002";
    private ListView lv1;
    AdapterDonHang ad;
    ArrayList<ThongTinDonHang> data = new ArrayList<>();

    TextView tvName, tvEmail, tvSDT;
    ImageView img;

    private ListView lv1;
    ArrayList<ThongTinDonHang> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_thongtinuser);

        setControl();
        setEvent();

    }

    public void setControl() {
        lv1 = findViewById(R.id.lv1);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvSDT = findViewById(R.id.tvSDT);
        img = findViewById(R.id.img);
    }

    public void setEvent() {

        khoiTao();
        ad = new AdapterDonHang(ThongTinUser.this, R.layout.listview_donhang_trangthai, data);
        lv1.setAdapter(ad);
    }

    public void khoiTao() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_NGUOI_MUA.status_data && PetShopFireBase.TABLE_DON_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data && PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.status_data){
                    NguoiMua nguoiMua = ((ArrayList<NguoiMua>)PetShopFireBase.search("id", id, PetShopFireBase.TABLE_NGUOI_MUA)).get(0);
                    tvName.setText(nguoiMua.getName());
                    tvEmail.setText(nguoiMua.getUsername());
                    tvSDT.setText(nguoiMua.getPhone());
                    //
                    ArrayList<DonHang> listDonHang = (ArrayList<DonHang>) PetShopFireBase.TABLE_DON_HANG.getData();
                    for(DonHang donHang:listDonHang){
                        if(donHang.getId_nguoi_mua().equals(id)){
                            SanPham sanPham = ((ArrayList<SanPham>)PetShopFireBase.search("id", donHang.getId_san_pham(), PetShopFireBase.TABLE_SAN_PHAM)).get(0);
                            TinhTrangDonHang tinhTrangDonHang = ((ArrayList<TinhTrangDonHang>)PetShopFireBase.search("id", String.valueOf(donHang.getTinh_trang()), PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).get(0);
                            data.add(new ThongTinDonHang(sanPham.getName(), tinhTrangDonHang.getName(), "Đánh giá"));
                        }
                    }
                    ad.notifyDataSetChanged();

                }
                else handler.postDelayed(this,1000);
            }
        });
    }
}