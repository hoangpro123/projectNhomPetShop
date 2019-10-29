package tdc.edu.vn.project.Screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import androidx.fragment.app.Fragment;
import tdc.edu.vn.project.Adapter.AdapterDonHangNguoiMua;
import tdc.edu.vn.project.InforUserActivity;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.HoaHong;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;


import com.squareup.otto.Subscribe;


public class ThongTinUser extends Fragment {

    //static String idnm = "nm002";
    private ListView lv1;
    AdapterDonHangNguoiMua adapter;
    ArrayList<DonHang> data;
    Button btnChinhSua;
    TextView tvName, tvEmail, tvSDT;
    ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.layout_thongtinuser);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_thongtinuser, null);
        PetShopFireBase.bus.register(this);
        setControl(view);
        setEvent();
        return view;
    }

    public void setControl(View view) {
        lv1 = view.findViewById(R.id.lv1);
        btnChinhSua = (Button) view.findViewById(R.id.btnFix);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvSDT = view.findViewById(R.id.tvSDT);
        img = view.findViewById(R.id.img);
    }

    public void setEvent() {
        khoiTao();
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InforUserActivity.class);
//                String a = intent.getStringExtra("id");
//                textView.setText(a);
                startActivity(intent);
            }
        });
    }

    public void khoiTao() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SaveId", Context.MODE_PRIVATE);
        final String idnm = sharedPreferences.getString("id", "");
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_NGUOI_MUA.status_data && PetShopFireBase.TABLE_DON_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data && PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.status_data) {

                    NguoiMua nguoiMua = (NguoiMua) PetShopFireBase.findItem(idnm,PetShopFireBase.TABLE_NGUOI_MUA);
                    tvName.setText(nguoiMua.getName());
                    tvEmail.setText(nguoiMua.getUsername());
                    tvSDT.setText(nguoiMua.getPhone());
                    //
                    data = (ArrayList<DonHang>) PetShopFireBase.search("id_nguoi_mua",idnm,PetShopFireBase.TABLE_DON_HANG);
                    adapter = new AdapterDonHangNguoiMua(getContext(), R.layout.listview_donhang_trangthai, data);
                    lv1.setAdapter(adapter);
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    @Subscribe
    public void onChanged(String table_name) {
        if (table_name.equals(PetShopFireBase.TABLE_NGUOI_MUA.getName()) || table_name.equals(PetShopFireBase.TABLE_DON_HANG.getName()) || table_name.equals(PetShopFireBase.TABLE_SAN_PHAM.getName()) || table_name.equals(PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.getName())) {
            khoiTao();
        }
    }
}