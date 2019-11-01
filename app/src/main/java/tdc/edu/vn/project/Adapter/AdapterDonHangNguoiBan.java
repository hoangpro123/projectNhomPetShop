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
import tdc.edu.vn.project.Model.TinhTrangDonHang;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.Screen.ChiTietDonHang;
import tdc.edu.vn.project.Screen.ChonNguoiGiaoScreen;

public class AdapterDonHangNguoiBan extends ArrayAdapter<DonHang> {
    private Context context;
    private int layout;
    private ArrayList<DonHang> data;


    public AdapterDonHangNguoiBan(Context context, int layout, ArrayList<DonHang> data) {
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
            holder.tvID_DON_HANG = view.findViewById(R.id.tvID_DON_HANG);
            holder.tvTINH_TRANG = view.findViewById(R.id.tvTINH_TRANG);
            holder.spn = view.findViewById(R.id.spn);
            holder.btn = view.findViewById(R.id.btn);

            view.setTag(holder);
        } else holder = (DonHangNguoiBanHolder) view.getTag();
        //
        DonHang item = data.get(position);

        holder.llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietDonHang.class);
                intent.putExtra("iddh",item.getId());
                context.startActivity(intent);
            }
        });

        holder.tvID_DON_HANG.setText(item.getId());

        TinhTrangDonHang tinhTrangDonHang = (TinhTrangDonHang) PetShopFireBase.findItem(String.valueOf(item.getTinh_trang()), PetShopFireBase.TABLE_TINH_TRANG_DON_HANG);

        holder.tvTINH_TRANG.setText(tinhTrangDonHang.getName());

        ArrayList<String> dsSpinner = new ArrayList<>();
        switch (item.getTinh_trang()) {
            case 0:
                dsSpinner.add(((TinhTrangDonHang) PetShopFireBase.findItem("1", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).getName());
                dsSpinner.add(((TinhTrangDonHang) PetShopFireBase.findItem("5", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).getName());
                break;
            case 1:
                dsSpinner.add(((TinhTrangDonHang) PetShopFireBase.findItem("2", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).getName());
                dsSpinner.add(((TinhTrangDonHang) PetShopFireBase.findItem("5", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).getName());
                break;
            case 2:
                dsSpinner.add(((TinhTrangDonHang) PetShopFireBase.findItem("3", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).getName());
                dsSpinner.add(((TinhTrangDonHang) PetShopFireBase.findItem("5", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).getName());
                break;
        }
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
                TinhTrangDonHang dangGiaoHang = (TinhTrangDonHang) PetShopFireBase.findItem("2", PetShopFireBase.TABLE_TINH_TRANG_DON_HANG);
                if (dsSpinner.get(i).equals(dangGiaoHang.getName())) {
                    holder.btn.setText("Chọn");
                    holder.btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, ChonNguoiGiaoScreen.class);
                            intent.putExtra("iddh", item.getId());
                            context.startActivity(intent);
                        }
                    });
                }
                else {
                    holder.btn.setText("Lưu");
                    holder.btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TinhTrangDonHang tinhTrangSelected = ((ArrayList<TinhTrangDonHang>) PetShopFireBase.search("name", holder.spn.getSelectedItem().toString(), PetShopFireBase.TABLE_TINH_TRANG_DON_HANG)).get(0);
                            item.setTinh_trang(Integer.parseInt(tinhTrangSelected.getId()));
                            PetShopFireBase.pushItem(item, PetShopFireBase.TABLE_DON_HANG);
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return view;
    }

    static class DonHangNguoiBanHolder {
        LinearLayout llClick;
        TextView tvID_DON_HANG;
        TextView tvTINH_TRANG;
        Spinner spn;
        Button btn;
    }
}
