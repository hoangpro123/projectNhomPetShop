package tdc.edu.vn.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DanhSachThuCungActivity extends AppCompatActivity {
    Spinner spDanhMuc, spLoai, spGia;
    TextView tvDanhMuc, tvLoai, tvGia;
    List<Pet> listPet ;
    String[] stringDanhMuc;
    String[] stringGia;
    String[] stringLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_thu_cung);

        //set data
        setData();
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
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,listPet);
        stringDanhMuc = getResources().getStringArray(R.array.danhmuc);
        stringGia = getResources().getStringArray(R.array.gia);
        stringLoai = getResources().getStringArray(R.array.loai);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);
    }

    private void setData() {
        listPet = new ArrayList<>();
        listPet.add(new Pet("The Vegitarian","500000","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Wild Robot","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("Maria Semples","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Martian","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("He Died with...","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Wild Robot","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("Maria Semples","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Martian","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("He Died with...","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Wild Robot","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("Maria Semples","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Martian","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("He Died with...","Categorie Book","Description book", R.drawable.petshop));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book", R.drawable.petshop));
    }
    private void setSpinner(String[] array, Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}
