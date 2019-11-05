
package tdc.edu.vn.project;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Model.SanPham;

public class ChiTietThuCungNguoiBanActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    Button Back, Repair, Save;
    SanPham sanPham;
    String ID;
    int i = 0;
    private TextView tvtitle, tvdescription, tvprice ;
    private EditText  edTitle, edDescription, edPrice;
    private ImageView imgThemSanPham;
    SliderLayout sliderLayout;
    ArrayList<byte[]> arrBytes = new ArrayList<>();
    ArrayList<String> image_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thu_cung_nguoi_ban);
        setControl();
        setEvent();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 69 && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 96 && resultCode == RESULT_OK && data != null) {
            arrBytes.clear();
            ArrayList<Uri> list_uri = new ArrayList<>();
            if (data.getClipData() != null) {
                ClipData clipData = data.getClipData();
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    list_uri.add(clipData.getItemAt(i).getUri());
                }
            } else if (data.getData() != null) {
                list_uri.add(data.getData());
            }
            imgThemSanPham.setImageURI(list_uri.get(0));
            try {
                for (Uri uri : list_uri) {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    arrBytes.add(bytes);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setEvent() {
        // Recieve nguoiBan
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID_SanPham");
        String Title = intent.getStringExtra("Title");
        String price = intent.getStringExtra("Price");
        String Description = intent.getStringExtra("Description");
        String image = intent.getStringExtra("Thumbnail");

        // Setting values
        tvtitle.setText(Title);
        tvprice.setText(price);
        tvdescription.setText(Description);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, 96);
            }
        });


        Repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repair.setVisibility(View.GONE);
                Save.setVisibility(View.VISIBLE);

                tvtitle.setVisibility(View.GONE);
                edTitle.setText(tvtitle.getText());
                edTitle.setVisibility(View.VISIBLE);

                tvprice.setVisibility(View.GONE);
                edPrice.setVisibility(View.VISIBLE);
                edPrice.setText(tvprice.getText());

                tvdescription.setVisibility(View.GONE);
                edDescription.setText(tvdescription.getText());
                edDescription.setVisibility(View.VISIBLE);

                sliderLayout.setVisibility(View.GONE);
                imgThemSanPham.setVisibility(View.VISIBLE);
                Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Save.setVisibility(View.GONE);
                        Repair.setVisibility(View.VISIBLE);

                        edTitle.setVisibility(View.GONE);
                        tvtitle.setText(edTitle.getText());
                        tvtitle.setVisibility(View.VISIBLE);

                        edPrice.setVisibility(View.GONE);
                        tvprice.setText(edPrice.getText());
                        tvprice.setVisibility(View.VISIBLE);

                        edDescription.setVisibility(View.GONE);
                        tvdescription.setText(edDescription.getText());
                        tvdescription.setVisibility(View.VISIBLE);

                        imgThemSanPham.setVisibility(View.GONE);
                        sliderLayout.setVisibility(View.VISIBLE);
                        if (arrBytes.size() == 0) {
                            Toast.makeText(getApplicationContext(), "Chưa chọn hình", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Đã chọn hình", Toast.LENGTH_SHORT).show();
                            upload(arrBytes,0);
                        }
                    }
                });
            }
        });
    }

    private void setControl() {
        tvtitle = (TextView) findViewById(R.id.tvTenThuCung);
        tvdescription = (TextView) findViewById(R.id.tvThongTinThuCung);
        tvprice = (TextView) findViewById(R.id.tvGiaThuCung);
        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        Back = (Button) findViewById(R.id.btnBack);
        Repair = (Button) findViewById(R.id.btnRepair);
        Save = (Button) findViewById(R.id.btnSave);
        edTitle = (EditText) findViewById(R.id.edTenThuCung);
        edPrice = (EditText) findViewById(R.id.edGiaThuCung);
        edDescription = (EditText) findViewById(R.id.edThongTinThuCung);
        imgThemSanPham = (ImageView) findViewById(R.id.imgThemSanPham);
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
        sliderLayout.removeAllSliders();
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
    private void upload(ArrayList<byte[]> arrBytes, int i) {
        if (i >= arrBytes.size()) return;
        UploadTask uploadTask = PetShopFireBase.fireBaseStorage.child("img" + Calendar.getInstance().getTimeInMillis()).putBytes(arrBytes.get(i));
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        String link = task.getResult().toString();
                        image_list.add(link);
                        if(i == arrBytes.size() - 1){
                            SanPham sp = (SanPham) PetShopFireBase.findItem(ID, PetShopFireBase.TABLE_SAN_PHAM);
                            sp.setName(tvtitle.getText().toString());
                            sp.setPrice(Double.parseDouble(tvprice.getText().toString()));
                            sp.setDescription(tvdescription.getText().toString());
                            if(arrBytes.size() != 0){
                                sp.setImages_list(image_list);
                            }
                            PetShopFireBase.pushItem(sp, PetShopFireBase.TABLE_SAN_PHAM);
                            AddImagesUrlOnline();
                        }
                        else {
                            upload(arrBytes, i+1);
                        }
                    }
                });
            }
        });
    }
}