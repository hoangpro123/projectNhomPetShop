package tdc.edu.vn.project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterDonHang extends ArrayAdapter<ThongTinDonHang> {
    Context context;
    int layoutrs;
    ArrayList<ThongTinDonHang> data = null;

    public AdapterDonHang(Context context, int layoutrs, ArrayList<ThongTinDonHang> data) {
        super(context, layoutrs, data);
        this.context = context;
        this.layoutrs = layoutrs;
        this.data = data;
    }

    public View getView(int position, View cView, ViewGroup parent) {
        View row = cView;
        CountryHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutrs, parent, false);
            holder = new CountryHolder();
            holder.cbTenHang = (CheckBox) row.findViewById(R.id.cbTenHang);
            holder.txtTrangThai = (TextView) row.findViewById(R.id.txtTrangThai);
            holder.btnDanhGia = (Button) row.findViewById(R.id.btnDanhGia);
            row.setTag(holder);
        } else {
            holder = (CountryHolder) row.getTag();
        }
        ThongTinDonHang item = data.get(position);
        holder.btnDanhGia.setVisibility(View.INVISIBLE);
        if (item.trangThai.equals("Hoàn thành")) holder.btnDanhGia.setVisibility(View.VISIBLE);
        holder.cbTenHang.setText(item.tenHang);
        holder.txtTrangThai.setText(item.trangThai);
        holder.btnDanhGia.setText(item.danhGia);
        return row;
    }

    static class CountryHolder {
        CheckBox cbTenHang;
        TextView txtTrangThai;
        Button btnDanhGia;
    }
}