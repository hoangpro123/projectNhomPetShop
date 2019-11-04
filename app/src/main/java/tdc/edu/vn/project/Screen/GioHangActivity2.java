package tdc.edu.vn.project.Screen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Adapter.LV_GioHangAdapter;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.Adapter.LV_GioHangAdapter.GioHangHolder;
import tdc.edu.vn.project.User.ChangePass;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GioHangActivity2 extends AppCompatActivity {

    String id;
    ListView listView;
    ArrayList<GioHang> data = new ArrayList<>();
    LV_GioHangAdapter adapter;
    ImageButton btnBack;
    Button btnDelete, btnDatMua;
    CheckBox checkBox, btnSeclectAll;
    TextView tvTotal;

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
        btnDatMua = findViewById(R.id.btnDatMua);
        checkBox = findViewById(R.id.checkbox);
        btnSeclectAll = findViewById(R.id.btnChonTatCa);
        tvTotal = findViewById(R.id.txtTotal);
        SharedPreferences sharedPreferences = getSharedPreferences("SaveId", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
    }

    private void setEvent() {
        khoiTao();
        datMua();
        deleteItem();
        tinhTongTien();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(GioHangActivity2.this, data.get(i).toString(), Toast.LENGTH_SHORT).show();

            }
        });
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(GioHangActivity2.this, "ssssss", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        btnSeclectAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int size = data.size();
//                for(int i = 0; i < size; i++){
//                    //GioHang gh = data.get(i);
//                    checkBox.setChecked(true);
//
//                }
//            }
//        });

        btnSeclectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (int i = 0; i < listView.getChildCount(); i++) {
                    GioHangHolder holder = (GioHangHolder) listView.getChildAt(i).getTag();
                    holder.getCheckBox().setChecked(b);
                }
            }
        });


    }

    private void tinhTongTien() {
        double tongTien = 0;
        for (int i = 0; i < listView.getChildCount(); i++) {
            GioHangHolder holder = (GioHangHolder) listView.getChildAt(i).getTag();
            holder.getCheckBox().setChecked(true);
            tongTien += Double.parseDouble(holder.getDonGia().getText().toString());
        }
        tvTotal.setText(String.valueOf(tongTien));
    }

    private void khoiTao() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_GIO_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data) {
                    data = (ArrayList<GioHang>) PetShopFireBase.search("id_nguoi_mua", id, PetShopFireBase.TABLE_GIO_HANG);
                    adapter = new LV_GioHangAdapter(GioHangActivity2.this, R.layout.listview_item, data);
                    listView.setAdapter(adapter);
                } else {
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    private void deleteItem() {
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
                        for (int n = 0; n < listView.getChildCount(); n++) {

                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void datMua() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //data = (ArrayList<GioHang>) PetShopFireBase.search("id_nguoi_mua", id, PetShopFireBase.TABLE_GIO_HANG);
                btnDatMua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        for (int i = 0; i < data.size(); i++) {
//                            data.remove(i);
//                            adapter.notifyDataSetChanged();
//                        }
                        if (PetShopFireBase.TABLE_DON_HANG.status_data) {
                            GioHang gh = (GioHang) PetShopFireBase.findItem(id, PetShopFireBase.TABLE_GIO_HANG);
                            String idSP = gh.getId_san_pham().toString();
                            DonHang donhang = new DonHang(id,idSP,1,0,(double)100);
                            PetShopFireBase.pushItem(donhang, PetShopFireBase.TABLE_DON_HANG);
                            PetShopFireBase.removeItem(id,PetShopFireBase.TABLE_GIO_HANG);
                            adapter.notifyDataSetChanged();
                        }else{
                            handler.postDelayed(null, 1000);
                        }
                    }
                });
            }
        });
    }
}
