package tdc.edu.vn.project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.R;

public class LV_GioHangAdapter extends ArrayAdapter<GioHang> {
    Context context;
    int layoutResourceId;
    ArrayList<GioHang> data = null;

    public LV_GioHangAdapter(Context context, int layoutResourceId, ArrayList<GioHang> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
    static class GioHangHolder{
        TextView tenSP, donGia, id_nguoimua, id_sanpham;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        GioHangHolder holder = null;
        {
            if(row != null)
            {
                holder = (GioHangHolder)row.getTag();
            } else
            {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new GioHangHolder();
                //holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
                holder.tenSP = (TextView)row.findViewById(R.id.tenSP);
                holder.donGia = (TextView)row.findViewById(R.id.dongia);
                row.setTag(holder);
            }
            GioHang gh = data.get(position);
           // holder.tenSP.setText("yyuy");
//            holder.donGia.setText(gh.getDonGia());
//            holder.id_sanpham.setText(gh.getId_san_pham());
//            holder.id_nguoimua.setText(gh.getId_nguoi_mua());
            return row;
        }
    }
}
