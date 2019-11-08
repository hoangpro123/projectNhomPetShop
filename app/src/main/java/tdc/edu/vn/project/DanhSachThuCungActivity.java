package tdc.edu.vn.project;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import tdc.edu.vn.project.Adapter.RecyclerViewAdapter;
import tdc.edu.vn.project.Model.SanPham;

import static java.util.Locale.getDefault;


public class DanhSachThuCungActivity extends AppCompatActivity {
    Spinner spDanhMuc, spGia;
    Button Back;
    SearchView searchView;
    RecyclerViewAdapter myAdapter;
    ArrayList<SanPham> data;
    DatabaseReference mDatabase;
    String[] stringDanhMuc;
    String[] stringGia;
    String[] stringLoai;
    String loai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_thu_cung);

        //set nguoiBan
        setControl();
        setEvent();


    }

    private void setEvent() {
        Intent intent = getIntent();
        loai = intent.getStringExtra("Loại");
        setSpinner(stringGia, spGia);
        setSpinner(stringDanhMuc, spDanhMuc);
        getFirebaseSanPham();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        spDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spDanhMuc.getSelectedItem().toString().equals("Tất cả")){
                    myAdapter.filter("");
                }else if(spDanhMuc.getSelectedItem().toString().equals("Khác")){
                    myAdapter.filter("");
                }else {
                    myAdapter.filter(spDanhMuc.getSelectedItem().toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spGia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if(stringGia[position].equals("Thấp đến cao")){
                    PetShopFireBase.sortList( "price", PetShopFireBase.TABLE_SAN_PHAM, true);
                }else{
                    PetShopFireBase.sortList( "price", PetShopFireBase.TABLE_SAN_PHAM, false);
                }
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(PetShopFireBase.TABLE_SAN_PHAM.status_last_id  && PetShopFireBase.TABLE_SAN_PHAM.status_data){
                            myAdapter.notifyDataSetChanged();
                        }
                        else handler.postDelayed(this, 1000);
                    }
                });
            };
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                myAdapter.filter(s);
                return false;
            }
        });
    }
    private void setControl() {
        spDanhMuc = (Spinner)findViewById(R.id.SpDanhMuc);
        spGia = (Spinner)findViewById(R.id.SpGia);
        searchView = (SearchView) findViewById(R.id.searchview);
        Back = (Button) findViewById(R.id.btnBack);
        stringDanhMuc = getResources().getStringArray(R.array.danhmuc);
        stringGia = getResources().getStringArray(R.array.gia);
        stringLoai = getResources().getStringArray(R.array.loai);
    }
    private void getFirebaseSanPham() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_SAN_PHAM.status_last_id  && PetShopFireBase.TABLE_SAN_PHAM.status_data){
                    data = (ArrayList<SanPham>)PetShopFireBase.TABLE_SAN_PHAM.data;
                    RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview);
                    myAdapter = new RecyclerViewAdapter(DanhSachThuCungActivity.this,data);
                    myrv.setLayoutManager(new GridLayoutManager(DanhSachThuCungActivity.this,2));
                    myrv.setAdapter(myAdapter);

                    for(int i = 0; i < stringDanhMuc.length; i++){
                        String s1 = removeAccent(stringDanhMuc[i]).toLowerCase(Locale.getDefault());
                        String s2 = removeAccent(loai).toLowerCase(Locale.getDefault());
                        if(s1.equals(s2)){
                            spDanhMuc.setSelection(i);
                        }
                    }
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

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }


}
