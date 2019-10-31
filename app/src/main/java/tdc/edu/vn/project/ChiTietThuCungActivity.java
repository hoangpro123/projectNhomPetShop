
package tdc.edu.vn.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.util.HashMap;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class ChiTietThuCungActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private TextView tvtitle,tvdescription,tvprice;
    private ImageView img;
    HashMap<String, Integer> HashMapForLocalRes ;
    SliderLayout sliderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thu_cung);
        setControl();
        setEvent();w
    }

    private void setEvent() {
        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String price = intent.getExtras().getString("Price");
        String Description = intent.getExtras().getString("Description");
        String image = intent.getExtras().getString("Thumbnail") ;

        // Setting values
        tvtitle.setText(Title);
        tvprice.setText(price);
        tvdescription.setText(Description);
//        Picasso.with(this).load(image).into(img);


        AddImageUrlFormLocalRes();

        for(String name : HashMapForLocalRes.keySet()){

            TextSliderView textSliderView = new TextSliderView(ChiTietThuCungActivity.this);

            textSliderView
                    .description(name)
                    .image(HashMapForLocalRes.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(ChiTietThuCungActivity.this);


    }

    private void setControl() {
        tvtitle = (TextView) findViewById(R.id.tvTenThuCung);
        tvdescription = (TextView) findViewById(R.id.tvThongTinThuCung);
        tvprice = (TextView) findViewById(R.id.tvGiaThuCung);
        sliderLayout = (SliderLayout) findViewById(R.id.slider);
    }

    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void AddImageUrlFormLocalRes(){

        HashMapForLocalRes = new HashMap<String, Integer>();
        HashMapForLocalRes.put("Hinh1", R.drawable.petshop);
        HashMapForLocalRes.put("Hinh2", R.drawable.petshop);
        HashMapForLocalRes.put("Hinh3", R.drawable.petshop);
        HashMapForLocalRes.put("Hinh4", R.drawable.petshop);
        HashMapForLocalRes.put("Hinh5", R.drawable.petshop);

    }
}