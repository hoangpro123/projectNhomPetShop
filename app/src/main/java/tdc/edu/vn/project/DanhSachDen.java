package tdc.edu.vn.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DanhSachDen extends AppCompatActivity {

    private ListView lv3;
    ArrayList<DSDen> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachden);
        setControl();
        setEvent();
    }
    public void setControl(){
        lv3 = findViewById(R.id.lv3);
    }

    public void setEvent(){
        khoiTao();
        AdapterDanhSachDen ad = new AdapterDanhSachDen(this,R.layout.listview_danhsachden,data);
        lv3.setAdapter(ad);
    }

    public void khoiTao(){
        data.add(new DSDen("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        data.add(new DSDen("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        data.add(new DSDen("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        data.add(new DSDen("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
    }
}
