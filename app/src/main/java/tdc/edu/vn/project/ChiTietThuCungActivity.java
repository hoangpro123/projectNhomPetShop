package tdc.edu.vn.project;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.project.Adapter.AdapterDanhGia;
import tdc.edu.vn.project.Model.DanhGia;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.Screen.GioHangActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;

public class ChiTietThuCungActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    Button btnThem, btnShopCart, Back;
    SanPham sanPham;
    String idsp, idnm;
    RatingBar ratingBar;
    private TextView tvtitle, tvdescription, tvprice, tvTenCuaHang, tvDiaChi, tvSDT;
    private ImageView img;
    SliderLayout sliderLayout;

    ArrayList<DanhGia> data;
    AdapterDanhGia adapter;
    ListView lvDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thu_cung);
        setControl();
        setEvent();
    }

    private void setEvent() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_SAN_PHAM.status_data && PetShopFireBase.TABLE_NGUOI_BAN.status_data && PetShopFireBase.TABLE_DANH_GIA.status_data && PetShopFireBase.TABLE_NGUOI_MUA.status_data) {
                    SanPham sanPham = (SanPham) PetShopFireBase.findItem(idsp, PetShopFireBase.TABLE_SAN_PHAM);
                    NguoiBan nguoiBan = (NguoiBan)PetShopFireBase.findItem(sanPham.getId_nguoi_ban(),PetShopFireBase.TABLE_NGUOI_BAN);
                    ArrayList<DanhGia> list_danhgia = (ArrayList<DanhGia>) PetShopFireBase.search("id_nguoi_bi_danh_gia",nguoiBan.getId(),PetShopFireBase.TABLE_DANH_GIA);
                    float rating = 0;
                    for(DanhGia danhGia: list_danhgia){
                        rating += danhGia.getRate();
                    }
                    rating /= list_danhgia.size();
                    ratingBar.setRating(rating);
                    tvDiaChi.setText(nguoiBan.getAddress());
                    tvSDT.setText(nguoiBan.getPhone());
                    tvtitle.setText(sanPham.getName());
                    tvprice.setText(String.valueOf(sanPham.getPrice()));
                    tvdescription.setText(sanPham.getDescription());
                    tvTenCuaHang.setText(nguoiBan.getName());

                    data = (ArrayList<DanhGia>) PetShopFireBase.search("id_nguoi_bi_danh_gia",nguoiBan.getId(),PetShopFireBase.TABLE_DANH_GIA);
                    Toast.makeText(ChiTietThuCungActivity.this, data.size()+"", Toast.LENGTH_SHORT).show();
                    adapter = new AdapterDanhGia(ChiTietThuCungActivity.this, R.layout.listview_danhgia,data);
                    lvDanhGia.setAdapter(adapter);
//        Picasso.with(this).load(image).into(img);
                } else handler.postDelayed(this, 1000);

            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemGioHang();
            }
        });

        btnShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GioHangActivity.class));
            }
        });

    }

    private void setControl() {
        SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
        idnm = sharedPreferences.getString(PetShopSharedPreferences.idnm, null);
        //idnb = sharedPreferences.getString(PetShopSharedPreferences.idnb, null);
        Intent intent = getIntent();
        idsp = intent.getStringExtra("ID_SanPham");
        //
        ratingBar = findViewById(R.id.ratingBar);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        tvSDT = (TextView) findViewById(R.id.tvSDT);
        tvtitle = (TextView) findViewById(R.id.tvTenThuCung);
        tvdescription = (TextView) findViewById(R.id.tvThongTinThuCung);
        tvprice = (TextView) findViewById(R.id.tvGiaThuCung);
        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        Back = (Button) findViewById(R.id.btnBack);
        btnThem = findViewById(R.id.btnThem);
        btnShopCart = findViewById(R.id.btnShopCart);
        tvTenCuaHang = findViewById(R.id.tvTenCuaHang);
        lvDanhGia = findViewById(R.id.lvDanhGia);
        AddImagesUrlOnline();
    }

    public void AddImagesUrlOnline() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_SAN_PHAM.status_data) {
                    sanPham = (SanPham) PetShopFireBase.findItem(idsp, PetShopFireBase.TABLE_SAN_PHAM);

                    for (int i = 0; i < sanPham.getImages_list().size(); i++) {
                        TextSliderView textSliderView = new TextSliderView(ChiTietThuCungActivity.this);

                        textSliderView
                                .description("hình " + (i + 1))
                                .image(sanPham.getImages_list().get(i))
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(ChiTietThuCungActivity.this);

                        textSliderView.bundle(new Bundle());

                        textSliderView.getBundle()
                                .putString("extra", "hình " + (i + 1));

                        sliderLayout.addSlider(textSliderView);
                    }
                    sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

                    sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

                    sliderLayout.setCustomAnimation(new DescriptionAnimation());

                    sliderLayout.setDuration(3000);

                    sliderLayout.addOnPageChangeListener(ChiTietThuCungActivity.this);
                    //Toast.makeText(ChiTietThuCungNguoiBanActivity.this, sanPham.getImages_list() +"", Toast.LENGTH_SHORT).show();
                } else handler.postDelayed(this, 1000);
            }
        });

    }

    private void ThemGioHang() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_GIO_HANG.status_data) {
                    Intent intent = getIntent();
                    ArrayList<GioHang> listGH = (ArrayList<GioHang>) PetShopFireBase.search("id_nguoi_mua", intent.getStringExtra("IDNGMUA"), PetShopFireBase.TABLE_GIO_HANG);
                    GioHang gioHang = new GioHang(idnm, idsp, 1);

                    for (GioHang gh : listGH) {
                        if (gh.getId_san_pham().equals(idsp)) {
                            Toast.makeText(ChiTietThuCungActivity.this, "Da ton tai trong gio hang", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    PetShopFireBase.pushItem(gioHang, PetShopFireBase.TABLE_GIO_HANG);
                    Toast.makeText(ChiTietThuCungActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                } else handler.postDelayed(this, 1000);
            }
        });


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
}
