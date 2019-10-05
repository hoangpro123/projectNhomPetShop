package tdc.edu.vn.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import tdc.edu.vn.project.Model.PetShopModel;
import tdc.edu.vn.project.Model.SanPham;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext ;
    private ArrayList<SanPham> mData ;

    public RecyclerViewAdapter(Context mContext, ArrayList<SanPham> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
        holder.tv_price.setText(mData.get(position).getPrice().toString());
        holder.img_pet.setImageResource(Integer.parseInt(mData.get(position).getImage()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChiTietThuCungActivity.class);
                // passing data to the book activity
                intent.putExtra("Title",mData.get(position).getPrice().toString());
                intent.putExtra("Price", Integer.parseInt(mData.get(position).getImage()));
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Thumbnail",Integer.parseInt(mData.get(position).getImage()));
                // start the activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
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