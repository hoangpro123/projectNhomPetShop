package tdc.edu.vn.project;

import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Adapter.AdapterDanhSachDen;
import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.Screen.DanhSachDenScreen;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class DanhSachDenNguoiBan extends AppCompatActivity {
    private ListView lv3;
    AdapterDanhSachDen ad;
    SearchView searchView;
    ArrayList<DanhSachDen> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_den_nguoi_ban);
    }
    public void setControl(){
        lv3 = (ListView) findViewById(R.id.lv3);
        searchView = (SearchView)findViewById(R.id.sreach);
    }

    public void setEvent(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_DANH_SACH_DEN.status_data){

                    data = (ArrayList<DanhSachDen>) PetShopFireBase.TABLE_DANH_SACH_DEN.data;
                    //ad = new AdapterDanhSachDen(DanhMucNguoiBanActivity.this,R.layout.item_danh_sach_den,data);
                    //Toast.makeText(getApplication(), data.get(0).getId_nguoi_ban(), Toast.LENGTH_SHORT).show();
                    lv3.setAdapter(ad);
                    handler.postDelayed(this, 1000);

                }else {
                    handler.postDelayed(this, 1000);
                    //  Toast.makeText(getApplication(), "Không Có dữ liệu", Toast.LENGTH_SHORT).show();
                }

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ad.filter(newText);
                return false;
            }
        });


    }
}
