package tdc.edu.vn.project.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

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
    private double tongtien;
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
            holder.ty_le = view.findViewById(R.id.thanhtien);
            holder.id_don_hang = view.findViewById(R.id.tvID_DON_HANG);
            holder.thanhtien = view.findViewById(R.id.tvTINH_TRANG);
            holder.spn = view.findViewById(R.id.spn);
            holder.btn = view.findViewById(R.id.btn);
            holder.Capnhat = view.findViewById(R.id.dongcn);

            view.setTag(holder);
        } else holder = (DonHangNguoiBanHolder) view.getTag();
        //
        HoaHong item = data.get(position);
        holder.id_don_hang.setText(item.getId_don_hang());
        holder.thanhtien.setText(item.getTinh_trang_hoa_hong());
        holder.ty_le.setText(item.getSo_tien().toString());
        holder.llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietDonHang.class);
                intent.putExtra("iddh",item.getId());
                context.startActivity(intent);
            }
        });
        holder.Capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogfail(item);
            }
        });





        ArrayList<String> dsSpinner = new ArrayList<>();



        return view;
    }

    static class DonHangNguoiBanHolder {
        LinearLayout llClick;
        TextView ty_le;
        TextView thanhtien;
        TextView id_don_hang;
        Spinner spn;
        Button btn, Capnhat;
    }

    private void dialogfail(HoaHong item){
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialogchinhsuagiaodich);
        EditText maDH = (EditText)dialog.findViewById(R.id.edma);
        EditText tyle = (EditText)dialog.findViewById(R.id.edTile);
        RadioGroup radioGroup = (RadioGroup)dialog.findViewById(R.id.groupcheck);
        RadioButton dong = (RadioButton)dialog.findViewById(R.id.dong);
        RadioButton chuadong = (RadioButton)dialog.findViewById(R.id.chuadong);
        if (item.getTinh_trang_hoa_hong().toString().equals("Chưa đóng tiền")){
            chuadong.isChecked();
        }else {
            dong.isChecked();
        }
        int ischeck = radioGroup.getCheckedRadioButtonId();
        Button XacNhan = (Button)dialog.findViewById(R.id.accept);

        maDH.setText(item.getId_don_hang());
        tyle.setText(String.valueOf( item.getTy_le()));
        float tile = item.getTy_le();
        Button xoa = (Button)dialog.findViewById(R.id.del);
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        Date date = new Date();
        XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < donHangs.size(); i++){
                    if(item.getId_don_hang() == donHangs.get(i).getId()){

                    }
                }
                int check= radioGroup.getCheckedRadioButtonId();
                RadioButton checked = dialog.findViewById(check);
                item.setId_don_hang(maDH.getText().toString());

                double sotien = (item.getSo_tien()*100)/tile;

                item.setThoi_gian_dong_tien(date);
                item.setTinh_trang_hoa_hong(checked.getText().toString());
                item.setTy_le(Float.parseFloat( tyle.getText().toString()));
                Math.round(tongtien = sotien*(item.getTy_le()/100));
                item.setSo_tien((double) Math.round(tongtien));
                PetShopFireBase.pushItem(item, PetShopFireBase.TABLE_HOA_HONG);
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
