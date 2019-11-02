package tdc.edu.vn.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import tdc.edu.vn.project.Adapter.AdapterDonHangNguoiMua;
import tdc.edu.vn.project.Model.DonHang;

public class fragment_donHangNguoiMua extends Fragment {
    private ListView lv;

    private AdapterDonHangNguoiMua adapter;
    private ArrayList<DonHang> data;
    private String idnm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
        idnm = sharedPreferences.getString(PetShopSharedPreferences.idnm, null);

        PetShopFireBase.bus.register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_don_hang_nguoi_mua, container, false);

        setControl(view);
        khoiTao();

        return view;
    }
    private void khoiTao(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_DON_HANG.status_data){
                    data = (ArrayList<DonHang>) PetShopFireBase.search("id_nguoi_mua", idnm, PetShopFireBase.TABLE_DON_HANG);
                    adapter = new AdapterDonHangNguoiMua(getContext(), R.layout.listview_donhang_nguoimua, data);
                    lv.setAdapter(adapter);
                }else handler.postDelayed(this,1000);

            }
        });

    }
    private void setControl(View view){
        lv = view.findViewById(R.id.lv);
    }

    @Subscribe
    public void onChange(String table_name){
        if(table_name.equals(PetShopFireBase.TABLE_DON_HANG.getName()) || table_name.equals(PetShopFireBase.TABLE_SAN_PHAM.getName()) || table_name.equals(PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.getName())){
            khoiTao();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PetShopFireBase.bus.unregister(this);
    }
}
