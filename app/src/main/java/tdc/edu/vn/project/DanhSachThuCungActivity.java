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
import android.widget.Filter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import tdc.edu.vn.project.Model.PetShopModel;
import tdc.edu.vn.project.Model.SanPham;

import static java.util.Locale.getDefault;


public class DanhSachThuCungActivity extends AppCompatActivity {
    Spinner spDanhMuc, spLoai, spGia;
    TextView tvDanhMuc, tvLoai, tvGia;
    SearchView searchView;
    RecyclerViewAdapter myAdapter;
    ArrayList<SanPham> data;
    DatabaseReference mDatabase;
    String[] stringDanhMuc;
    String[] stringGia;
    String[] stringLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_thu_cung);

        //set data

        PetShopFireBase.loadTable(PetShopFireBase.TABLE_SAN_PHAM);

        setControl();
        setEvent();

    }

    private void setEvent() {
        setSpinner(stringDanhMuc, spDanhMuc);
        setSpinner(stringGia, spGia);
        setSpinner(stringLoai, spLoai);
        getFirebaseSanPham();

        spDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                if(stringDanhMuc[position].equals("Chó")){
//
//                }
//                final Handler handler = new Handler();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(PetShopFireBase.TABLE_SAN_PHAM.status_last_id && PetShopFireBase.TABLE_SAN_PHAM.status_count && PetShopFireBase.TABLE_SAN_PHAM.status_TABLE){
//                            myAdapter.notifyDataSetChanged();
//                        }
//                        else handler.postDelayed(this, 1000);
//                    }
//                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //tvLoai.setText(stringLoai[position]);
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
                //PetShopFireBase.sortList( "price", PetShopFireBase.TABLE_SAN_PHAM, true);
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(PetShopFireBase.TABLE_SAN_PHAM.status_last_id && PetShopFireBase.TABLE_SAN_PHAM.status_count && PetShopFireBase.TABLE_SAN_PHAM.status_TABLE){
                            myAdapter.notifyDataSetChanged();
                        }
                        else handler.postDelayed(this, 1000);
                    }
                });

                //PetShopFireBase.loadTable(PetShopFireBase.TABLE_SAN_PHAM);

            };
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(DanhSachThuCungActivity.this, s, Toast.LENGTH_SHORT).show();
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
        spLoai = (Spinner)findViewById(R.id.SpLoai);
        searchView = (SearchView) findViewById(R.id.searchview);

        //getFirebaseSanPham();

        stringDanhMuc = getResources().getStringArray(R.array.danhmuc);
        stringGia = getResources().getStringArray(R.array.gia);
        stringLoai = getResources().getStringArray(R.array.loai);

    }

    private void getFirebaseSanPham() {

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_SAN_PHAM.status_last_id && PetShopFireBase.TABLE_SAN_PHAM.status_count && PetShopFireBase.TABLE_SAN_PHAM.status_TABLE){
                    data = (ArrayList<SanPham>)PetShopFireBase.TABLE_SAN_PHAM.data;
                    RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview);
                    myAdapter = new RecyclerViewAdapter(DanhSachThuCungActivity.this,data);
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
    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
