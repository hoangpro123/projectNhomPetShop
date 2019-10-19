package tdc.edu.vn.project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterXacNhanDonHangNguoiBan extends ArrayAdapter<DonHangChoXacNhan> {
    Context context;
    int layoutrs;
    ArrayList<DonHangChoXacNhan> data = null;

    public AdapterXacNhanDonHangNguoiBan(Context context, int layoutrs, ArrayList<DonHangChoXacNhan> data) {
        super(context, layoutrs, data);
        this.context = context;
        this.layoutrs = layoutrs;
        this.data = data;
    }

    public View getView(int position, View cView, ViewGroup parent){
        View row = cView;
        CountryHolder holder = null;

        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutrs,parent,false);
            holder = new CountryHolder();
            holder.txtTenHang = (TextView) row.findViewById(R.id.txtTenSanPham);
            holder.txtThoiGian = (TextView) row.findViewById(R.id.txtThoiGian);
            holder.btnTrangThai = (Button) row.findViewById(R.id.btnTrangThai);
            row.setTag(holder);
        }
        else
        {
            holder = (CountryHolder)row.getTag();
        }
        DonHangChoXacNhan item = data.get(position);
        holder.txtTenHang.setText(item.tenHang);
        holder.txtThoiGian.setText(item.thoiGian);
        holder.btnTrangThai.setText(item.trangThai);
        return row;
    }

    static class CountryHolder{
        TextView txtTenHang;
        TextView txtThoiGian;
        Button btnTrangThai;
    }
}
