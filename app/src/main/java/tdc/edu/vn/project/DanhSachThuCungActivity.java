package tdc.edu.vn.project;

import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import tdc.edu.vn.project.Model.PetShopModel;
import tdc.edu.vn.project.Model.SanPham;


public class DanhSachThuCungActivity extends AppCompatActivity {
    Spinner spDanhMuc, spLoai, spGia;
    TextView tvDanhMuc, tvLoai, tvGia;

    ArrayList<SanPham> data;
    String[] stringDanhMuc;
    String[] stringGia;
    String[] stringLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_thu_cung);

        //set data


        setControl();
        setEvent();

    }

    private void setEvent() {
        setSpinner(stringDanhMuc, spDanhMuc);
        setSpinner(stringGia, spGia);
        setSpinner(stringLoai, spLoai);

        spDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvDanhMuc.setText(stringDanhMuc[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvLoai.setText(stringLoai[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spGia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvGia.setText(stringGia[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void setControl() {
        tvDanhMuc = (TextView) findViewById(R.id.tvDanhMuc);
        tvGia = (TextView) findViewById(R.id.tvGia);
        tvLoai = (TextView) findViewById(R.id.tvLoai);
        spDanhMuc = (Spinner)findViewById(R.id.SpDanhMuc);
        spGia = (Spinner)findViewById(R.id.SpGia);
        spLoai = (Spinner)findViewById(R.id.SpLoai);



        getFirebaseSanPham();



        stringDanhMuc = getResources().getStringArray(R.array.danhmuc);
        stringGia = getResources().getStringArray(R.array.gia);
        stringLoai = getResources().getStringArray(R.array.loai);



    }

    private void getFirebaseSanPham() {
        PetShopFireBase.loadTable(PetShopFireBase.TABLE_SAN_PHAM);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_SAN_PHAM.status_last_id && PetShopFireBase.TABLE_SAN_PHAM.status_count && PetShopFireBase.TABLE_SAN_PHAM.status_TABLE){
                    data = (ArrayList<SanPham>)PetShopFireBase.TABLE_SAN_PHAM.data;
                    RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview);
                    RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(DanhSachThuCungActivity.this,data);
                    myrv.setLayoutManager(new GridLayoutManager(DanhSachThuCungActivity.this,2));
                    myrv.setAdapter(myAdapter);
                }
                else handler.postDelayed(this, 1000);
            }
        });



    }

    private void setSpinner(String[] array, Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}
