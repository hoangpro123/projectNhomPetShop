package tdc.edu.vn.project;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
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

import java.util.ArrayList;

import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.QuanLy;
import tdc.edu.vn.project.Model.TinhTrangDonHang;

public class AdapterDonHangNguoiBan extends ArrayAdapter<DonHang> {
    Context context;
    int layout;
    ArrayList<DonHang> data;

    public AdapterDonHangNguoiBan(Context context, int layout, ArrayList<DonHang> data) {
        super(context, layout, data);
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    public View getView(int position, View view, ViewGroup parent) {
        CountryHolder holder;
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layout, parent, false);

            holder = new CountryHolder();
            holder.llClick = view.findViewById(R.id.llClick);
            holder.tvID_DON_HANG = view.findViewById(R.id.tvID_DON_HANG);
            holder.tvTINH_TRANG = view.findViewById(R.id.tvTINH_TRANG);
            holder.spn = view.findViewById(R.id.spn);
            holder.btn = view.findViewById(R.id.btn);

            view.setTag(holder);
        } else holder = (CountryHolder) view.getTag();
        //
        DonHang item = data.get(position);

        holder.llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.tvID_DON_HANG.setText(item.getId());

        TinhTrangDonHang tinhTrangDonHang = ((ArrayList<TinhTrangDonHang>) PetShopFireBase.search("id", String.valueOf(item.getTinh_trang()), PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).get(0);

        holder.tvTINH_TRANG.setText(tinhTrangDonHang.getName());

        ArrayList<String> dsSpinner = new ArrayList<>();
        switch (item.getTinh_trang()) {
            case 0:
                dsSpinner.add(((ArrayList<TinhTrangDonHang>) PetShopFireBase.search("id", "1", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).get(0).getName());
                dsSpinner.add(((ArrayList<TinhTrangDonHang>) PetShopFireBase.search("id", "5", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).get(0).getName());
                break;
            case 1:
                dsSpinner.add(((ArrayList<TinhTrangDonHang>) PetShopFireBase.search("id", "2", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).get(0).getName());
                dsSpinner.add(((ArrayList<TinhTrangDonHang>) PetShopFireBase.search("id", "5", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).get(0).getName());
                break;
            case 2:
                dsSpinner.add(((ArrayList<TinhTrangDonHang>) PetShopFireBase.search("id", "3", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).get(0).getName());
                dsSpinner.add(((ArrayList<TinhTrangDonHang>) PetShopFireBase.search("id", "5", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).get(0).getName());
                break;
        }
        if(dsSpinner.size() == 0){
            holder.spn.setVisibility(View.INVISIBLE);
            holder.btn.setVisibility(View.INVISIBLE);
        }
        else {
            holder.spn.setVisibility(View.VISIBLE);
            holder.btn.setVisibility(View.VISIBLE);
            //
            holder.spn.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dsSpinner));
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TinhTrangDonHang tinhTrangSelected = ((ArrayList<TinhTrangDonHang>)PetShopFireBase.search("name",holder.spn.getSelectedItem().toString(),PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).get(0);
                    item.setTinh_trang(Integer.parseInt(tinhTrangSelected.getId()));
                    PetShopFireBase.pushItem(item,PetShopFireBase.TABLE_DON_HANG);
                }
            });
        }



        return view;
    }

    static class CountryHolder {
        LinearLayout llClick;
        TextView tvID_DON_HANG;
        TextView tvTINH_TRANG;
        Spinner spn;
        Button btn;
    }
}
