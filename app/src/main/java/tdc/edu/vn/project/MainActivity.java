package tdc.edu.vn.project;


import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.project.Model.SanPham;


public class MainActivity extends AppCompatActivity {
Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PetShopFireBase.loadTable(PetShopFireBase.TABLE_SAN_PHAM);
        SanPham sp = new SanPham("a","a", "a","a","A","a");
        PetShopFireBase.pushItem(sp,PetShopFireBase.TABLE_SAN_PHAM);
    }
}
