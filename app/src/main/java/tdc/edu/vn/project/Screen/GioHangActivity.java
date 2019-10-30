
package tdc.edu.vn.project.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.project.Adapter.LV_GioHangAdapter;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class GioHangActivity extends AppCompatActivity {

    ListView listView;
    TextView txtTenSP, txtDonGia;
    Button btnDatMua;
    ArrayList<GioHang> listGioHang = new ArrayList<>();
    LV_GioHangAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        setControl();
        setEvent();
    }

    public void setControl() {
        listView = findViewById(R.id.lv);
        txtTenSP = findViewById(R.id.tenSP);
        txtDonGia = findViewById(R.id.dongia);
        btnDatMua = findViewById(R.id.btnDatMua);
    }

    public void setEvent() {
        //listGioHang = new ArrayList<>();
        String id = "cart002";
//        listGioHang.add(new GioHang("1","sa", "sá", "fsdf"));
//        listGioHang.add(new GioHang("1","sas", "23", "fsdsdadaf"));
        if (PetShopFireBase.TABLE_GIO_HANG.status_data) {
            GioHang gh = ((ArrayList<GioHang>) PetShopFireBase.search("id", id, PetShopFireBase.TABLE_GIO_HANG)).get(0);
//            NguoiMua nm = ((ArrayList<NguoiMua>) PetShopFireBase.search("id", id, PetShopFireBase.TABLE_NGUOI_MUA)).get(0);
////            ArrayList<GioHang> data = (ArrayList<GioHang>)PetShopFireBase.TABLE_GIO_HANG.data;
////            listGioHang = data;
//
//            txtTenSP.setText(nm.getName());
//            txtDonGia.setText(nm.getPhone());
            listGioHang.add(new GioHang(gh.getId_nguoi_mua(),gh.getId_san_pham(), gh.getDonGia(), gh.getTenSP()));

            adapter = new LV_GioHangAdapter(this, R.layout.listview_item, listGioHang);
            listView.setAdapter(adapter);
        }

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //trở lại trang chi tiết tương ứng
            }
        });
        //adapter.notifyDataSetChanged();
    }

}

