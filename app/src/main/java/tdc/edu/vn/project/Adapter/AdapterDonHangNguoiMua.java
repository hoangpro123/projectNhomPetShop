package tdc.edu.vn.project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.Model.TinhTrangDonHang;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.Screen.DanhGiaActivity;

public class AdapterDonHangNguoiMua extends ArrayAdapter<DonHang> {
    Context context;
    int layout;
    ArrayList<DonHang> data;

    public AdapterDonHangNguoiMua(Context context, int layout, ArrayList<DonHang> data) {
        super(context, layout, data);
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    public View getView(int position, View view, ViewGroup parent) {
        CountryHolder holder;
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layout, parent, false);

            holder = new CountryHolder();
            holder.cbTenHang =  view.findViewById(R.id.cbTenHang);
            holder.txtTrangThai = view.findViewById(R.id.txtTrangThai);
            holder.btnDanhGia = view.findViewById(R.id.btnDanhGia);

            view.setTag(holder);
        } else holder = (CountryHolder) view.getTag();

        DonHang donHang = data.get(position);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_SAN_PHAM.status_data && PetShopFireBase.TABLE_TINH_TRANG_DON_HANG.status_data){
                    SanPham sanPham = (SanPham) PetShopFireBase.findItem(donHang.getId_san_pham(), PetShopFireBase.TABLE_SAN_PHAM);;

                    TinhTrangDonHang tinhTrangDonHang = (TinhTrangDonHang) PetShopFireBase.findItem(String.valueOf(donHang.getTinh_trang()),PetShopFireBase.TABLE_TINH_TRANG_DON_HANG);
                    //
                    holder.cbTenHang.setText(sanPham.getName());

                    holder.txtTrangThai.setText(tinhTrangDonHang.getName());

                    TinhTrangDonHang daHoanThanh = (TinhTrangDonHang) PetShopFireBase.findItem("3",PetShopFireBase.TABLE_TINH_TRANG_DON_HANG);
                    if (tinhTrangDonHang.getName().equals(daHoanThanh.getName())) holder.btnDanhGia.setVisibility(View.VISIBLE);
                    else holder.btnDanhGia.setVisibility(View.INVISIBLE);
                    holder.btnDanhGia.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getContext(), DanhGiaActivity.class);

                            intent.putExtra("iddh",donHang.getId());
                            getContext().startActivity(intent);
                        }
                    });
                }else handler.postDelayed(this,1000);
            }
        });

        return view;
    }

    static class CountryHolder {
        CheckBox cbTenHang;
        TextView txtTrangThai;
        Button btnDanhGia;
    }
}