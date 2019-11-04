package tdc.edu.vn.project.Screen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.project.Adapter.LV_GioHangAdapter;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.PetShopSharedPreferences;
import tdc.edu.vn.project.R;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    String idnm;
    ListView listView;
    ArrayList<GioHang> data;
    ArrayList<Integer> list_checked_item;
    LV_GioHangAdapter adapter;
    ImageButton btnBack;
    Button btnDelete, btnDatMua;
    CheckBox ckbSeclectAll;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        setControl();
        setEvent();
    }


    private void setEvent() {
        khoiTao();

        btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datMua();
            }
        });

        ckbSeclectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;

                for (int i = 0; i < listView.getChildCount(); i++) {
                    LV_GioHangAdapter.GioHangHolder holder = (LV_GioHangAdapter.GioHangHolder) listView.getChildAt(i).getTag();
                    holder.getCheckBox().setChecked(checkBox.isChecked());
                }
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void tinhTongTien() {
        double tongTien = 0;
        for (int i = 0; i < list_checked_item.size(); i++) {
            SanPham sp = (SanPham) PetShopFireBase.findItem(data.get(list_checked_item.get(i)).getId_san_pham(), PetShopFireBase.TABLE_SAN_PHAM);
            tongTien += sp.getPrice();
        }
        tvTotal.setText(String.valueOf(tongTien));
    }

    private void khoiTao() {
        ckbSeclectAll.setChecked(false);
        list_checked_item.clear();
        tvTotal.setText("0");
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_GIO_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data) {
                    data = (ArrayList<GioHang>) PetShopFireBase.search("id_nguoi_mua", idnm, PetShopFireBase.TABLE_GIO_HANG);
                    adapter = new LV_GioHangAdapter(GioHangActivity.this, R.layout.listview_item, data);
                    listView.setAdapter(adapter);

                    listView.post(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < listView.getChildCount(); i++) {
                                LV_GioHangAdapter.GioHangHolder holder = (LV_GioHangAdapter.GioHangHolder) listView.getChildAt(i).getTag();
                                int i2 = i;
                                holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                        if (b) list_checked_item.add(i2);
                                        else {
                                            for (int j = 0; j < list_checked_item.size(); j++) {
                                                if (list_checked_item.get(j) == i2) {
                                                    list_checked_item.remove(j);
                                                    break;
                                                }
                                            }
                                        }
                                        tinhTongTien();
                                    }
                                });
                                holder.getCheckBox().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ckbSeclectAll.setChecked(list_checked_item.size() == data.size());
                                    }
                                });
                            }
                        }
                    });
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    private void deleteItem() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle(getResources().getString(R.string.tb));
                builder.setMessage("Bạn có muốn xóa không???");
                builder.setCancelable(false);
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(GioHangActivity.this, getResources().getString(R.string.koxoa), Toast.LENGTH_SHORT).show();
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
                if (PetShopFireBase.TABLE_GIO_HANG.status_data && PetShopFireBase.TABLE_SAN_PHAM.status_data) {
                    for(int i = 0; i < list_checked_item.size(); i++){
                        GioHang gioHang = data.get(list_checked_item.get(i));
                        SanPham sanPham = (SanPham) PetShopFireBase.findItem(gioHang.getId_san_pham(),PetShopFireBase.TABLE_SAN_PHAM);
                        DonHang donHang = new DonHang(idnm,sanPham.getId(),1,0,sanPham.getPrice());

                        PetShopFireBase.pushItem(donHang,PetShopFireBase.TABLE_DON_HANG);
                        PetShopFireBase.removeItem(gioHang.getId(),PetShopFireBase.TABLE_GIO_HANG);
                    }
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    private void setControl() {
        data = new ArrayList<>();
        list_checked_item = new ArrayList<>();

        btnBack = findViewById(R.id.btnBack);
        listView = findViewById(R.id.lv);
        btnDelete = findViewById(R.id.btnDelete);
        btnDatMua = findViewById(R.id.btnDatMua);
        ckbSeclectAll = findViewById(R.id.btnChonTatCa);
        tvTotal = findViewById(R.id.txtTotal);

        SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
        idnm = sharedPreferences.getString(PetShopSharedPreferences.idnm, null);

        PetShopFireBase.bus.register(this);
    }

    @Subscribe
    public void onChanged(String table_name) {
        if (table_name.equals(PetShopFireBase.TABLE_SAN_PHAM.getName()) || table_name.equals(PetShopFireBase.TABLE_GIO_HANG.getName())) {
            khoiTao();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PetShopFireBase.bus.unregister(this);
    }
}
