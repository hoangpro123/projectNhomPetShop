
package tdc.edu.vn.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Model.SanPham;

public class ChiTietThuCungNguoiBanActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    Button Back;
    SanPham sanPham;
    String ID;
    private TextView tvtitle, tvdescription, tvprice;
    private ImageView img;
    SliderLayout sliderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thu_cung_nguoi_ban);
        setControl();
        setEvent();
    }

    private void setEvent() {
        // Recieve nguoiBan
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        String Title = intent.getStringExtra("Title");
        String price = intent.getStringExtra("Price");
        String Description = intent.getStringExtra("Description");
        String image = intent.getStringExtra("Thumbnail");

        // Setting values
        tvtitle.setText(Title);
        tvprice.setText(price);
        tvdescription.setText(Description);
//        Picasso.with(this).load(image).into(img);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void setControl() {
        tvtitle = (TextView) findViewById(R.id.tvTenThuCung);
        tvdescription = (TextView) findViewById(R.id.tvThongTinThuCung);
        tvprice = (TextView) findViewById(R.id.tvGiaThuCung);
        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        Back = (Button) findViewById(R.id.btnBack);
        AddImagesUrlOnline();
//        SharedPreferences sharedPreferences = getSharedPreferences("saveIDNguoiBan", Context.MODE_PRIVATE);
//        String id_nguoi_ban = sharedPreferences.getString("id_nguoi_ban", "");
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

    public void AddImagesUrlOnline() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_SAN_PHAM.status_data) {
                    sanPham = (SanPham) PetShopFireBase.findItem(ID, PetShopFireBase.TABLE_SAN_PHAM);

                    for (int i = 0; i < sanPham.getImages_list().size(); i++) {
                        TextSliderView textSliderView = new TextSliderView(ChiTietThuCungNguoiBanActivity.this);

                        textSliderView
                                .description("hình " + (i + 1))
                                .image(sanPham.getImages_list().get(i))
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(ChiTietThuCungNguoiBanActivity.this);

                        textSliderView.bundle(new Bundle());

                        textSliderView.getBundle()
                                .putString("extra", "hình " + (i + 1));

                        sliderLayout.addSlider(textSliderView);
                    }
                    sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

                    sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

                    sliderLayout.setCustomAnimation(new DescriptionAnimation());

                    sliderLayout.setDuration(3000);

                    sliderLayout.addOnPageChangeListener(ChiTietThuCungNguoiBanActivity.this);
                    //Toast.makeText(ChiTietThuCungNguoiBanActivity.this, sanPham.getImages_list() +"", Toast.LENGTH_SHORT).show();
                } else handler.postDelayed(this, 1000);
            }
        });

    }
}