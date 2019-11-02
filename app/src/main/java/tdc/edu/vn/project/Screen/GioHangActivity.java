package tdc.edu.vn.project.Screen;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Adapter.LV_GioHangAdapter;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.R;

class GioHangActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<GioHang> data = new ArrayList<>();
    LV_GioHangAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        setControl();

        setEvent();
    }

    private void setControl() {
        listView = findViewById(R.id.lv);
    }

    private void setEvent(){
        adapter = new LV_GioHangAdapter(this, R.layout.listview_item, data);
        listView.setAdapter(adapter);
    }
}
