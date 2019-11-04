package tdc.edu.vn.project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.HoaHong;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.Model.TinhTrangDonHang;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.Screen.ChiTietDonHang;
import tdc.edu.vn.project.Screen.ChonNguoiGiaoScreen;

public class AdapterGiaoDich extends ArrayAdapter<HoaHong> {
    private Context context;
    private int layout;

    private ArrayList<HoaHong> data;
    ArrayList<DonHang> donHangs = (ArrayList<DonHang>)PetShopFireBase.TABLE_DON_HANG.data;
    ArrayList<SanPham> sanPhams = (ArrayList<SanPham>)PetShopFireBase.TABLE_DON_HANG.data;
    public AdapterGiaoDich(Context context, int layout, ArrayList<HoaHong> data) {
        super(context, layout, data);
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        DonHangNguoiBanHolder holder;
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layout, parent, false);

            holder = new DonHangNguoiBanHolder();
            holder.llClick = view.findViewById(R.id.llClick);
            holder.id_don_hang = view.findViewById(R.id.tvID_DON_HANG);
            holder.thanhtien = view.findViewById(R.id.tvTINH_TRANG);
            holder.spn = view.findViewById(R.id.spn);
            holder.btn = view.findViewById(R.id.btn);

            view.setTag(holder);
        } else holder = (DonHangNguoiBanHolder) view.getTag();
        //
        HoaHong item = data.get(position);

        holder.llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietDonHang.class);
                intent.putExtra("iddh",item.getId());
                context.startActivity(intent);
            }
        });


        


        ArrayList<String> dsSpinner = new ArrayList<>();

        if (dsSpinner.size() == 0) {
            holder.spn.setVisibility(View.INVISIBLE);
            holder.btn.setVisibility(View.INVISIBLE);
        } else {
            holder.spn.setVisibility(View.VISIBLE);
            holder.btn.setVisibility(View.VISIBLE);
            holder.spn.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dsSpinner));
        }
        holder.spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return view;
    }

    static class DonHangNguoiBanHolder {
        LinearLayout llClick;
        TextView ty_le;
        TextView thanhtien;
        TextView id_don_hang;
        Spinner spn;
        Button btn;
    }
}
