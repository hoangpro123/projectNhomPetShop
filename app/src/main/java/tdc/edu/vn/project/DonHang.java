package tdc.edu.vn.project;

import android.os.Bundle;
import android.view.Window;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class DonHang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        setContentView(R.layout.activity_don_hang);

        PetShopFireBase.pushItem(new tdc.edu.vn.project.Model.DonHang("sdf","df","df",1,3,(double) 123), PetShopFireBase.TABLE_DON_HANG);
    }
}
