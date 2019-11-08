package tdc.edu.vn.project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import tdc.edu.vn.project.ChiTietThuCungActivity;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class LV_GioHangAdapter extends ArrayAdapter<GioHang> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<GioHang> data;

    public LV_GioHangAdapter(Context context, int layoutResourceId, ArrayList<GioHang> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        GioHangHolder holder;
        {
            if (row != null) {
                holder = (GioHangHolder) row.getTag();
            } else {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new GioHangHolder();
                holder.checkBox = (CheckBox) row.findViewById(R.id.checkbox);
                holder.tenSP = (TextView) row.findViewById(R.id.tenSP);
                holder.donGia = (TextView) row.findViewById(R.id.dongia);
                holder.llClick = row.findViewById(R.id.llClick);
                row.setTag(holder);
            }
            GioHang gh = data.get(position);
            SanPham sp = (SanPham) PetShopFireBase.findItem(gh.getId_san_pham(), PetShopFireBase.TABLE_SAN_PHAM);
            holder.tenSP.setText(sp.getName());
            holder.donGia.setText(String.valueOf(sp.getPrice()));
            holder.llClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChiTietThuCungActivity.class);
                    intent.putExtra("ID_SanPham",sp.getId());
                    context.startActivity(intent);
                }
            });

            return row;
        }
    }


    public class GioHangHolder {
        TextView tenSP, donGia;
        CheckBox checkBox;
        LinearLayout llClick;

        public TextView getTenSP() {
            return tenSP;
        }

        public void setTenSP(TextView tenSP) {
            this.tenSP = tenSP;
        }

        public TextView getDonGia() {
            return donGia;
        }

        public void setDonGia(TextView donGia) {
            this.donGia = donGia;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public LinearLayout getLlClick() {
            return llClick;
        }

        public void setLlClick(LinearLayout llClick) {
            this.llClick = llClick;
        }
    }
}

