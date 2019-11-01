package tdc.edu.vn.project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import tdc.edu.vn.project.Model.NguoiGiao;
import tdc.edu.vn.project.R;

public class AdapterNguoiGiao extends ArrayAdapter<NguoiGiao> {
    private Context context;
    private int layout;
    private ArrayList<NguoiGiao> data;
    public AdapterNguoiGiao(@NonNull Context context, int layout, @NonNull ArrayList<NguoiGiao> data) {
        super(context, layout, data);
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        NguoiGiaoHolder holder;
        if(view == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(layout, parent, false);
            holder = new NguoiGiaoHolder();

            holder.id_nguoi_giao = view.findViewById(R.id.id_nguoi_giao);
            holder.ten_nguoi_giao = view.findViewById(R.id.ten_nguoi_giao);
            holder.sdt_nguoi_giao = view.findViewById(R.id.sdt_nguoi_giao);
            holder.trang_thai_nguoi_giao = view.findViewById(R.id.trang_thai_nguoi_giao);

            view.setTag(holder);
        }else holder = (NguoiGiaoHolder) view.getTag();

        NguoiGiao item = data.get(position);
        holder.id_nguoi_giao.setText(item.getId());
        holder.ten_nguoi_giao.setText(item.getName());
        holder.sdt_nguoi_giao.setText(item.getPhone());
        holder.trang_thai_nguoi_giao.setText(item.getTinh_trang());
        view.setClickable(!item.getTinh_trang().equals("F"));
        return view;
    }
    private class NguoiGiaoHolder{
        TextView id_nguoi_giao, ten_nguoi_giao, sdt_nguoi_giao, trang_thai_nguoi_giao;
    }
}
