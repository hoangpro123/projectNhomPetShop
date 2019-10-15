package tdc.edu.vn.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ThongTinUser extends AppCompatActivity {
    private ListView lv1;
    ArrayList<ThongTinDonHang> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongtinuser);
        setControl();
        setEvent();
    }
    public void setControl(){
        lv1 = findViewById(R.id.lv1);
    }

    public void setEvent(){
        khoiTao();
        AdapterDonHang ad = new AdapterDonHang(this,R.layout.listview_donhang_trangthai,data);
        lv1.setAdapter(ad);
    }

    public void khoiTao(){
        data.add(new ThongTinDonHang("Mèo Anh Lông ngắn","Hoàn Thành","Đánh giá"));
        data.add(new ThongTinDonHang("Cậu Vàng","Đang giao","Đánh giá"));
        data.add(new ThongTinDonHang("Cậu Vàng","Đang giao","Đánh giá"));
        data.add(new ThongTinDonHang("Cậu Vàng","Đang giao","Đánh giá"));
        data.add(new ThongTinDonHang("Cậu Vàng","Đang giao","Đánh giá"));
        data.add(new ThongTinDonHang("Cậu Vàng","Đang giao","Đánh giá"));


    }
}
