package tdc.edu.vn.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class QuanLyDonHangNguoiBan extends AppCompatActivity {

    private ListView lv1;
    ArrayList<DonHangChoXacNhan> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quanlydonhangnguoiban);
        setControl();
        setEvent();
    }
    public void setControl(){
        lv1 = findViewById(R.id.lv2);
    }

    public void setEvent(){
        khoiTao();
        AdapterXacNhanDonHangNguoiBan ad = new AdapterXacNhanDonHangNguoiBan(this,R.layout.listview_xacnhan_nguoiban,data);
        lv1.setAdapter(ad);
    }

    public void khoiTao(){
        data.add(new DonHangChoXacNhan("meomeo","20/10 - 8:15","XN"));
        data.add(new DonHangChoXacNhan("meomeo","20/10 - 8:15","XN"));
        data.add(new DonHangChoXacNhan("meomeo","20/10 - 8:15","XN"));
    }
}
