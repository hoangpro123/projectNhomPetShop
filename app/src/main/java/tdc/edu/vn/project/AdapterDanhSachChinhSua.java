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

public class AdapterDanhSachChinhSua extends ArrayAdapter<DSChinhSua> {
    Context context;
    int layoutrs;
    ArrayList<DSChinhSua> data = null;

    public AdapterDanhSachChinhSua(Context context, int layoutrs, ArrayList<DSChinhSua> data) {
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
            holder.ivHinh = (ImageView) row.findViewById(R.id.ivHinh);
            holder.txtHoTen = (TextView) row.findViewById(R.id.txtHoTen);
            holder.txtEmail = (TextView) row.findViewById(R.id.txtEmail);
            holder.txtSDT = (TextView) row.findViewById(R.id.txtSDT);
            row.setTag(holder);
        }
        else
        {
            holder = (CountryHolder)row.getTag();
        }
        DSChinhSua item = data.get(position);
        holder.ivHinh.setImageResource(R.drawable.meo1);
        holder.txtHoTen.setText(item.hoTen);
        holder.txtEmail.setText(item.email);
        holder.txtSDT.setText(item.soDienThoai);
        return row;
    }

    static class CountryHolder{
        ImageView ivHinh;
        TextView txtHoTen;
        TextView txtEmail;
        TextView txtSDT;
    }
}
