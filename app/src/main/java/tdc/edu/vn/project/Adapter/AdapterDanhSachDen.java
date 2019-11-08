package tdc.edu.vn.project.Adapter;

import android.app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiGiao;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.Screen.DanhSachDenScreen;

import static java.util.Locale.getDefault;

public class AdapterDanhSachDen extends ArrayAdapter<DanhSachDen> {

    Activity context;
    int layoutrs;
    ArrayList<DanhSachDen> data;
    ArrayList<DanhSachDen> mdata = new ArrayList<>();
    ArrayList<NguoiBan> nguoiban;
    ArrayList<NguoiMua> nguoimua;
    public AdapterDanhSachDen(@NonNull Activity context, int resource, @NonNull ArrayList<DanhSachDen> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutrs = resource;
        this.data = objects;
        this.mdata.addAll(data);
    }

    public View getView(int position, View cView, ViewGroup parent){
       View row = cView;
        CountryHolder holder;

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
        if (PetShopFireBase.TABLE_NGUOI_BAN.status_data){
            nguoiban = (ArrayList<NguoiBan>) PetShopFireBase.TABLE_NGUOI_BAN.data;
            for(int j = 0; j < nguoiban.size(); j++){
                if (item.getId_nguoi_ban().toString().equals(nguoiban.get(j).getId())){
                    holder.txtHoTen.setText(nguoiban.get(j).getName());
                    holder.txtEmail.setText(nguoiban.get(j).getUsername());
                    holder.txtSDT.setText(nguoiban.get(j).getPhone());

                }
            }
        }
        if (PetShopFireBase.TABLE_NGUOI_MUA.status_data){
            nguoimua = (ArrayList<NguoiMua>) PetShopFireBase.TABLE_NGUOI_MUA.data;
            for(int j = 0; j < nguoimua.size(); j++){
                if (item.getId_nguoi_ban().toString().equals(nguoimua.get(j).getId())){
                    holder.txtHoTen.setText(nguoimua.get(j).getName());
                    holder.txtEmail.setText(nguoimua.get(j).getUsername());
                    holder.txtSDT.setText(nguoimua.get(j).getPhone());

                }
            }
        }
        holder.ivHinh.setImageResource(R.drawable.meo1);


        return row;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(getDefault());
        //removeAccent(charText);
        data.clear();
        if(charText.length() == 0){
            data.addAll(mdata);
        }else {
            for (DanhSachDen danhSachDen : mdata){
                if(removeAccent(danhSachDen.getId_nguoi_ban()).toLowerCase(Locale.getDefault()).contains(charText) || removeAccent(danhSachDen.getId_nguoi_mua()).toLowerCase(Locale.getDefault()).contains(charText)){
                    data.add(danhSachDen);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    static class CountryHolder{
        SearchView searchView;
        ImageView ivHinh;
        TextView txtHoTen;
        TextView txtEmail;
        TextView txtSDT;
        Button Xoa;
    }
}
