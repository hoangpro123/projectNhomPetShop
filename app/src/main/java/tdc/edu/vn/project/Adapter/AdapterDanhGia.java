package tdc.edu.vn.project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import tdc.edu.vn.project.Model.DanhGia;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class AdapterDanhGia extends ArrayAdapter<DanhGia> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<DanhGia> data;

    public AdapterDanhGia(Context context, int layoutResourceId, ArrayList<DanhGia> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        danhGiaHolder holder;
        {
            if (row != null) {
                holder = (danhGiaHolder) row.getTag();
            } else {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new danhGiaHolder();
                holder.tvKhachHang = (TextView) row.findViewById(R.id.tvKhachHang);
                holder.tvDanhGiaKhachHang = (TextView) row.findViewById(R.id.tvDanhGiaKhachHang);
                row.setTag(holder);
            }
            DanhGia dg = data.get(position);
            NguoiMua nguoiMua = (NguoiMua) PetShopFireBase.findItem(dg.getId_nguoi_danh_gia(),PetShopFireBase.TABLE_NGUOI_MUA);
            holder.tvKhachHang.setText(nguoiMua.getName());
            holder.tvDanhGiaKhachHang.setText(dg.getContent());

            return row;
        }
    }


    public class danhGiaHolder {
        TextView tvKhachHang, tvDanhGiaKhachHang;
    }
}

