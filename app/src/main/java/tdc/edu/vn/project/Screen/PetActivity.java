package tdc.edu.vn.project.Screen;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import tdc.edu.vn.project.R;

public class PetActivity extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvprice;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
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
