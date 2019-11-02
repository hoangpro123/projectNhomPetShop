package tdc.edu.vn.project.Screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
import android.widget.Toast;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import tdc.edu.vn.project.Adapter.AdapterDonHangNguoiMua;
import tdc.edu.vn.project.InforUserActivity;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.PetShopSharedPreferences;
import tdc.edu.vn.project.R;


import com.squareup.otto.Subscribe;


public class ThongTinUser extends Fragment {
    private String idnm;

    private Button btnChinhSua, btnLogout;
    private TextView tvName, tvEmail, tvSDT;
    private ImageView img;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_thongtinuser, null);
        PetShopFireBase.bus.register(this);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
        idnm = sharedPreferences.getString(PetShopSharedPreferences.idnm, null);
        setControl(view);
        setEvent();
        return view;
    }

    public void setControl(View view) {
        btnChinhSua = (Button) view.findViewById(R.id.btnFix);
        btnLogout = view.findViewById(R.id.btnLogout_nguoimua);
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
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PetShopSharedPreferences.idnm, null).apply();
                startActivity(new Intent(getContext(), Login.class));
                getActivity().finish();
            }
        });
    }

    public void khoiTao() {

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_NGUOI_MUA.status_data) {
                    NguoiMua nguoiMua = (NguoiMua) PetShopFireBase.findItem(idnm,PetShopFireBase.TABLE_NGUOI_MUA);
                    Toast.makeText(getContext(), "" + idnm, Toast.LENGTH_SHORT).show();
                    tvName.setText(nguoiMua.getName());
                    tvEmail.setText(nguoiMua.getUsername());
                    tvSDT.setText(nguoiMua.getPhone());
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    @Subscribe
    public void onChanged(String table_name) {
        if (table_name.equals(PetShopFireBase.TABLE_NGUOI_MUA.getName())) {
            khoiTao();
        }
    }
}