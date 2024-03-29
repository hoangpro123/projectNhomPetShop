package tdc.edu.vn.project.Adapter;

import android.app.Activity;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiGiao;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.Screen.DanhSachDenScreen;

import static java.util.Locale.getDefault;

public class AdapterQuanLiNguoiDung extends ArrayAdapter<NguoiMua> {

    Activity context;
    int layoutrs;
    ArrayList<NguoiMua> data;
    ArrayList<NguoiMua> mdata;

    public AdapterQuanLiNguoiDung(@NonNull Activity context, int resource, @NonNull ArrayList<NguoiMua> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutrs = resource;
        this.data = data;

        mdata = new ArrayList<>();
        mdata.addAll(data);
    }

    @NonNull
    public View getView(int position, View cView,@NonNull ViewGroup parent) {
        View row = cView;
        CountryHolder holder;

        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(layoutrs, parent, false);

            holder = new CountryHolder();
            holder.ivHinh = (ImageView) row.findViewById(R.id.imgNguoiDung);
            holder.txtid = (TextView) row.findViewById(R.id.txtHo);

            holder.txtEmail = (TextView) row.findViewById(R.id.txtmail);
            holder.txtname = (TextView) row.findViewById(R.id.txtsdt);
            holder.Xoa = (Button) row.findViewById(R.id.btnXoa);
            row.setTag(holder);
        } else holder = (CountryHolder) row.getTag();

        NguoiMua item = mdata.get(position);
        holder.txtid.setText(item.getId());
        holder.txtname.setText(item.getName());
        if (item.getImage() != null)
            Picasso.with(context).load(Uri.parse(item.getImage())).into(holder.ivHinh);
        holder.txtEmail.setText(item.getUsername());
        holder.Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialognguoimua(item);
            }
        });

        return row;
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(getDefault());
        //removeAccent(charText);
        data.clear();
        if (charText.length() == 0) {
            data.addAll(mdata);
        } else {
            for (NguoiMua nguoiMua : mdata) {
                if (removeAccent(nguoiMua.getName()).toLowerCase(Locale.getDefault()).contains(removeAccent(charText))) {
                    data.add(nguoiMua);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    static class CountryHolder {
        SearchView searchView;
        ImageView ivHinh;
        TextView txtid;
        TextView txtEmail;
        TextView txtname;
        Button Xoa;
    }

    private void dialognguoimua(final NguoiMua item) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_nguoi_dung);
        TextView id, name, user, pass, phone, adress, gender;
        ImageView img;
        id = (TextView) dialog.findViewById(R.id.txtid);
        name = (TextView) dialog.findViewById(R.id.txtname);
        user = (TextView) dialog.findViewById(R.id.txusername);
        pass = (TextView) dialog.findViewById(R.id.txpass);
        phone = (TextView) dialog.findViewById(R.id.txdienthoai);
        adress = (TextView) dialog.findViewById(R.id.txdiachi);
        gender = (TextView) dialog.findViewById(R.id.txgioitinh);
        img = (ImageView) dialog.findViewById(R.id.imgnguoi);
        id.setText(item.getId());
        name.setText(item.getName());
        user.setText(item.getUsername());
        pass.setText(item.getPassword());
        phone.setText(item.getPhone());
        adress.setText(item.getAddress());
        gender.setText(item.getGender());
        if (item.getImage() != null)
            Picasso.with(context).load(Uri.parse(item.getImage())).into(img);
        Button XacNhan = (Button) dialog.findViewById(R.id.xacnhan);
        XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        Button xoa = (Button) dialog.findViewById(R.id.xoa);
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetShopFireBase.removeItem(item.getId(), PetShopFireBase.TABLE_NGUOI_MUA);
                xoa.setVisibility(View.INVISIBLE);
                notifyDataSetChanged();
            }
        });
        dialog.show();
    }
}
