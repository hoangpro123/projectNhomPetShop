package tdc.edu.vn.project.Screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.util.HashMap;

import tdc.edu.vn.project.Model.GiaoHang;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class ChiTietThuCung extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout ;
    TextView Name, Price, Des;
    Button btnThemGioHang, btnMua;
    ImageButton btnGioHang;
    ImageButton btnBack;
    HashMap<String, String> HashMapForURL ;

    HashMap<String, Integer> HashMapForLocalRes ;

    ArrayList<SanPham> listPet ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietthucung);

        sliderLayout = (SliderLayout)findViewById(R.id.slider);
        AnhXa();
        GetInfor();
        setEvent();
        //Call this method if you want to add images from URL .
        //AddImagesUrlOnline();

        //Call this method to add images from local drawable folder .
        AddImageUrlFormLocalRes();

        //Call this method to stop automatic sliding.
        //sliderLayout.stopAutoCycle();

        for(String name : HashMapForLocalRes.keySet()){

            TextSliderView textSliderView = new TextSliderView(ChiTietThuCung.this);

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

        sliderLayout.addOnPageChangeListener(ChiTietThuCung.this);
    }

    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImageUrlFormLocalRes(){

        HashMapForLocalRes = new HashMap<String, Integer>();
        HashMapForLocalRes.put("Hinh1", R.drawable.meo1);
        HashMapForLocalRes.put("Hinh2", R.drawable.meo2);
        HashMapForLocalRes.put("Hinh3", R.drawable.meo3);
        HashMapForLocalRes.put("Hinh4", R.drawable.meo4);
        HashMapForLocalRes.put("Hinh5", R.drawable.meo5);
    }

    public void AnhXa(){
        btnThemGioHang = findViewById(R.id.btnThem);
        Name = findViewById(R.id.NamePet);
        Price = findViewById(R.id.Price);
        Des = findViewById(R.id.des);
        btnGioHang = findViewById(R.id.btnGioHang);
        btnBack = findViewById(R.id.btnBack);
        btnMua = findViewById(R.id.btnMua);
    }

    public void GetInfor(){
        Intent intent = getIntent();
        String name, price, des;
        Name.setText(intent.getStringExtra("Title"));
        Price.setText(intent.getStringExtra("Price"));
        Des.setText(intent.getStringExtra("Description"));


//        PetShopFireBase.TABLE_GIO_HANG.name.toString();
//        GioHangActivity gioHang = new GioHangActivity(intent.getStringExtra("IDNGMUA").toString(), intent.getStringExtra("ID").toString(),1);
//        Log.d("gh",gioHang.getId_nguoi_mua());
//        PetShopFireBase.pushItem(gioHang,PetShopFireBase.TABLE_GIO_HANG);
    }

    public void setEvent(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                PetShopFireBase.TABLE_GIO_HANG.name.toString();
                GioHang gioHang = new GioHang(intent.getStringExtra("IDNGMUA").toString(), intent.getStringExtra("ID").toString(),1);
                Log.d("gh",gioHang.getId_nguoi_mua());
                PetShopFireBase.pushItem(gioHang,PetShopFireBase.TABLE_GIO_HANG);
                intent = new Intent(ChiTietThuCung.this, GioHangActivity.class);
                startActivity(intent);
            }
        });

//        SharedPreferences sharedPreferences = getSharedPreferences("SaveId", Context.MODE_PRIVATE);
//        final String id = sharedPreferences.getString("id", "");
//        final Handler handler = new Handler();
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                PetShopFireBase.TABLE_GIO_HANG.name.toString();
                GioHang gioHang = new GioHang(intent.getStringExtra("IDNGMUA").toString(), intent.getStringExtra("ID").toString(),1);
                Log.d("gh",gioHang.getId_nguoi_mua());
                PetShopFireBase.pushItem(gioHang,PetShopFireBase.TABLE_GIO_HANG);
                Toast.makeText(ChiTietThuCung.this,"Đã thêm vào giỏ hàng" , Toast.LENGTH_SHORT).show();
            }
        });

        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietThuCung.this, GioHangActivity.class));
            }
        });
    }
}