package tdc.edu.vn.project.Screen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Adapter.LV_GioHangAdapter;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.User.ChangePass;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GioHangActivity2 extends AppCompatActivity {

    String id;
    ListView listView;
    ArrayList<GioHang> data = new ArrayList<>();
    LV_GioHangAdapter adapter;
    ImageButton btnBack;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        setControl();

        setEvent();
    }

    private void setControl() {
        btnBack = findViewById(R.id.btnBack);
        listView = findViewById(R.id.lv);
        btnDelete = findViewById(R.id.btnDelete);
        SharedPreferences sharedPreferences = getSharedPreferences("SaveId", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
    }

    private void setEvent(){
        khoiTao();
        deleteItem();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "asdjjka", Toast.LENGTH_SHORT).show();
            }
        });
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(GioHangActivity2.this, "ssssss", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }
    private void khoiTao(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if( PetShopFireBase.TABLE_GIO_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data){
                    data = (ArrayList<GioHang>) PetShopFireBase.search("id_nguoi_mua", id, PetShopFireBase.TABLE_GIO_HANG);
                    adapter = new LV_GioHangAdapter(GioHangActivity2.this, R.layout.listview_item, data);
                    listView.setAdapter(adapter);
                }else {
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    private void deleteItem(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity2.this);
                builder.setTitle(getResources().getString(R.string.tb));
                builder.setMessage("Bạn có muốn xóa không???");
                builder.setCancelable(false);
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(GioHangActivity2.this, getResources().getString(R.string.koxoa), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.co), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
            }
        });
    }
}
