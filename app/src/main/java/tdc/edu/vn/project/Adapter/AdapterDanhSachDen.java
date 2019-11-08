package tdc.edu.vn.project.Adapter;

import android.app.Activity;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

import static java.util.Locale.getDefault;

public class AdapterDanhSachDen extends ArrayAdapter<DanhSachDen> {
    Activity context;
    private int layoutrs;
    ArrayList<DanhSachDen> data;
    private ArrayList<DanhSachDen> mdata;

    public AdapterDanhSachDen(@NonNull Activity context, int resource, @NonNull ArrayList<DanhSachDen> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutrs = resource;
        this.data = data;
        mdata = new ArrayList<>();
        mdata.addAll(data);
    }

    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        DanhSachDenHolder holder;
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(layoutrs, parent, false);
            holder = new DanhSachDenHolder();
            holder.ivHinh = (ImageView) view.findViewById(R.id.ivHinh);
            holder.txtHoTen = (TextView) view.findViewById(R.id.txtHoTen);

            holder.txtEmail = (TextView) view.findViewById(R.id.txtEmail);
            holder.txtSDT = (TextView) view.findViewById(R.id.txtSDT);
            holder.Xoa = (Button) view.findViewById(R.id.btnXoa);
            view.setTag(holder);
        } else holder = (DanhSachDenHolder) view.getTag();

        DanhSachDen item = data.get(position);
        NguoiMua nguoiMua = (NguoiMua) PetShopFireBase.findItem(item.getId_nguoi_mua(), PetShopFireBase.TABLE_NGUOI_MUA);
        holder.txtHoTen.setText(nguoiMua.getName());
        holder.txtEmail.setText(nguoiMua.getUsername());
        holder.txtSDT.setText(nguoiMua.getPhone());
        if (nguoiMua.getImage() != null)
            Picasso.with(context).load(Uri.parse(nguoiMua.getImage())).into(holder.ivHinh);
        holder.Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.Xoa.setVisibility(View.INVISIBLE);
                PetShopFireBase.removeItem(item.getId(),PetShopFireBase.TABLE_DANH_SACH_DEN);
            }
        });

        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(getDefault());
        //removeAccent(charText);
        mdata.clear();
        if (charText.length() == 0) {
            mdata.addAll(data);
        } else {
            for (DanhSachDen danhSachDen : data) {
                if (removeAccent(danhSachDen.getId_nguoi_ban()).toLowerCase(Locale.getDefault()).contains(charText) || removeAccent(danhSachDen.getId_nguoi_mua()).toLowerCase(Locale.getDefault()).contains(charText)) {
                    mdata.add(danhSachDen);
                }
            }
        }
        notifyDataSetChanged();
    }

    private static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    static class DanhSachDenHolder {
        SearchView searchView;
        ImageView ivHinh;
        TextView txtHoTen;
        TextView txtEmail;
        TextView txtSDT;
        Button Xoa;
    }
}
