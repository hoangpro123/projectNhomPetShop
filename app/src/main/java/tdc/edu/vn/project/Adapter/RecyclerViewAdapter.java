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

import java.util.ArrayList;
import java.util.List;

import tdc.edu.vn.project.Screen.ChiTietThuCung;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.R;

import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import tdc.edu.vn.project.ChiTietThuCungActivity;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.R;

import static java.util.Locale.getDefault;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext ;
    private ArrayList<SanPham> mData ;
    private ArrayList<SanPham> arrayList ;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    public RecyclerViewAdapter(Context mContext, ArrayList<SanPham> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.arrayList = new ArrayList<SanPham>();
        this.arrayList.addAll(mData);
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
                Intent intent = new Intent(mContext, ChiTietThuCungActivity.class);
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("SaveId", Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("id", "");
                // passing data to the book activity
                intent.putExtra("ID", mData.get(position).getId());
                intent.putExtra("IDNGMUA", id);
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
                if(removeAccent(sanPham.getName()).toLowerCase(Locale.getDefault()).contains(charText)){
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