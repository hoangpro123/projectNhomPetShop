package tdc.edu.vn.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.project.ChiTietThuCungActivity;
import tdc.edu.vn.project.ChiTietThuCungNguoiBanActivity;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopSharedPreferences;
import tdc.edu.vn.project.R;

import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import static java.util.Locale.getDefault;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext ;
    private ArrayList<SanPham> mData ;
    private ArrayList<SanPham> arrayList ;
    String id_nguoi_mua;
    String id_nguoi_ban;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    public RecyclerViewAdapter(Context mContext, ArrayList<SanPham> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.arrayList = new ArrayList<SanPham>();
        this.arrayList.addAll(mData);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
        this.id_nguoi_mua = sharedPreferences.getString(PetShopSharedPreferences.idnm, null);
        this.id_nguoi_ban = sharedPreferences.getString(PetShopSharedPreferences.idnb, null);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_view_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_pet_title.setText(mData.get(position).getName());
        holder.tv_price.setText(String.valueOf(mData.get(position).getPrice()));
        Picasso.with(mContext).load(Uri.parse(mData.get(position).getImages_list().get(0))).into(holder.img_pet);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(id_nguoi_mua == null){
                    intent = new Intent(mContext, ChiTietThuCungNguoiBanActivity.class);
                    Toast.makeText(mContext, "NguoiBan", Toast.LENGTH_SHORT).show();
                }else{
                    intent = new Intent(mContext, ChiTietThuCungActivity.class);
                    Toast.makeText(mContext, "NguoiMua", Toast.LENGTH_SHORT).show();
                }
                // passing data to the book activity
                intent.putExtra("ID_SanPham", mData.get(position).getId());
                intent.putExtra("IDNGMUA", id_nguoi_mua);
                intent.putExtra("Title",mData.get(position).getName());
                intent.putExtra("Price", mData.get(position).getPrice().toString());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Thumbnail",mData.get(position).getImages_list());
                // start the activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(getDefault());
        mData.clear();
        if(charText.length() == 0){
            mData.addAll(arrayList);
        }else {
            for (SanPham sanPham : arrayList){
                if(removeAccent(sanPham.getName()).toLowerCase(Locale.getDefault()).contains(removeAccent(charText))){
                    mData.add(sanPham);
                }
            }
        }
        notifyDataSetChanged();
    }
    public void DanhMuc(String s){
        s = s.toLowerCase(getDefault());
        mData.clear();
        if(s.length() == 0){
            mData.addAll(arrayList);
        }else {
            for (SanPham sanPham : arrayList){
                if(removeAccent(sanPham.getName()).toLowerCase(Locale.getDefault()).contains(s)){
                    mData.add(sanPham);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_pet_title;
        ImageView img_pet;
        CardView cardView ;
        TextView tv_price;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_pet_title = (TextView) itemView.findViewById(R.id.pettitle) ;
            tv_price = (TextView) itemView.findViewById(R.id.price);
            img_pet = (ImageView) itemView.findViewById(R.id.petimg);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}