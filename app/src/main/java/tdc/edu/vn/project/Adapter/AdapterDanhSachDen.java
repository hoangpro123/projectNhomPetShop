package tdc.edu.vn.project.Adapter;

import android.app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import java.util.ArrayList;

import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiGiao;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.Screen.DanhSachDenScreen;

public class AdapterDanhSachDen extends ArrayAdapter<DanhSachDen> {

    Activity context;
    int layoutrs;
    ArrayList<DanhSachDen> data;

    public AdapterDanhSachDen(@NonNull Activity context, int resource, @NonNull ArrayList<DanhSachDen> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutrs = resource;
        this.data = objects;
    }

    public View getView(int position, View cView, ViewGroup parent){
       View row = cView;
        CountryHolder holder = null;

        if (row == null){
            LayoutInflater inflater =  context.getLayoutInflater();
            row = inflater.inflate(layoutrs,parent,false);
            holder = new CountryHolder();
            holder.ivHinh = (ImageView) row.findViewById(R.id.ivHinh);
            holder.txtHoTen = (TextView) row.findViewById(R.id.txtHoTen);
            holder.txtEmail = (TextView) row.findViewById(R.id.txtEmail);
            holder.txtSDT = (TextView) row.findViewById(R.id.txtSDT);
            holder.Xoa = (Button)row.findViewById(R.id.btnXoa);
            row.setTag(holder);
        }
        else
        {
            holder = (CountryHolder)row.getTag();
        }

        final DanhSachDen item = data.get(position);
        if (PetShopFireBase.TABLE_NGUOI_MUA.status_data || PetShopFireBase.TABLE_NGUOI_BAN.status_data){
            ArrayList<NguoiMua> dataNguoiMua = (ArrayList<NguoiMua>) PetShopFireBase.TABLE_NGUOI_MUA.data;
            ArrayList<NguoiBan> dataNguoiBan = (ArrayList<NguoiBan>) PetShopFireBase.TABLE_NGUOI_BAN.data;
            for (int i = 0; i < dataNguoiMua.size(); i++){
                if (dataNguoiMua.get(i).getId().equals(item.getId_nguoi_mua())){
                    holder.txtHoTen.setText(dataNguoiMua.get(i).getName());
                    holder.txtEmail.setText(dataNguoiMua.get(i).getUsername());
                    holder.txtSDT.setText(dataNguoiMua.get(i).getPhone());
                    holder.Xoa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PetShopFireBase.removeItem(item.getId_nguoi_mua(), PetShopFireBase.eTable.DanhSachDen);
                            notifyDataSetChanged();
                        }
                    });
                }

            }
            for (int i = 0; i < dataNguoiBan.size(); i++){
                if (dataNguoiBan.get(i).getId().equals(item.getId_nguoi_mua())){
                    holder.txtHoTen.setText(dataNguoiBan.get(i).getName());
                    holder.txtEmail.setText(dataNguoiBan.get(i).getUsername());
                    holder.txtSDT.setText(dataNguoiBan.get(i).getPhone());
                    holder.Xoa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PetShopFireBase.removeItem(item.getId_nguoi_mua(), PetShopFireBase.eTable.DanhSachDen);
                            notifyDataSetChanged();
                        }
                    });
                }
            }
        }
        holder.ivHinh.setImageResource(R.drawable.meo1);


        return row;
    }

    static class CountryHolder{
        ImageView ivHinh;
        TextView txtHoTen;
        TextView txtEmail;
        TextView txtSDT;
        Button Xoa;
    }
}
