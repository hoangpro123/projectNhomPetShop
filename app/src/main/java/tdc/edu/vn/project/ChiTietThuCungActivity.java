package tdc.edu.vn.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChiTietThuCungActivity extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvprice;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thu_cung);
        setControl();
        setEvent();
    }

    private void setEvent() {
        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String price = intent.getExtras().getString("Price");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail") ;

        // Setting values
        tvtitle.setText(Title);
        tvprice.setText(price);
        tvdescription.setText(Description);
        img.setImageResource(image);
    }

    private void setControl() {
        tvtitle = (TextView) findViewById(R.id.txttitle);
        tvdescription = (TextView) findViewById(R.id.txtDesc);
        tvprice = (TextView) findViewById(R.id.price);
        img = (ImageView) findViewById(R.id.bookthumbnail);
    }
}
