package tdc.edu.vn.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class YeuCauChinhSua extends AppCompatActivity {

    private ListView lvDSChinhSua;
    ArrayList<DSChinhSua> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachyeucauchinhsua);
        setControl();
        setEvent();
    }
    public void setControl(){
        lvDSChinhSua = findViewById(R.id.lvDSChinhSua);
    }

    public void setEvent(){
        khoiTao();
        AdapterDanhSachChinhSua ad = new AdapterDanhSachChinhSua(this,R.layout.listview_danhsachchinhsua,data);
        lvDSChinhSua.setAdapter(ad);
    }

    public void khoiTao(){
        data.add(new DSChinhSua("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        data.add(new DSChinhSua("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        data.add(new DSChinhSua("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
        data.add(new DSChinhSua("ad","nhan ahihi","nhanahihi@gmail.com","0377878784"));
    }
}
