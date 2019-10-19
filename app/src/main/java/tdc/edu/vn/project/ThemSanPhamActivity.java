package tdc.edu.vn.project;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tdc.edu.vn.project.Adapter.RecyclerViewAdapter;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.SanPham;

public class ThemSanPhamActivity extends AppCompatActivity {
    String id = "null";
    List<NguoiBan> data;
    Button Back, DangTin;
    ImageButton ThemSanPham;
    EditText TieuDe, ThongTinSanPham, Gia;
    TextView ThongTinNguoiBan;
    String link;
    String currentDateTimeString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        getFirebaseSanPham();
        setControl();
        setEvent();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 69 && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[0] == PackageManager.PERMISSION_GRANTED){
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 96 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            ThemSanPham.setImageURI(uri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                //
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] bytes = byteArrayOutputStream.toByteArray();

                UploadTask uploadTask = PetShopFireBase.fireBaseStorage.child("img" + Calendar.getInstance().getTimeInMillis()).putBytes(bytes);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                link = task.getResult().toString();

                            }
                        });
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void setControl() {
        ThemSanPham = (ImageButton) findViewById(R.id.imgThemSanPham);
        TieuDe = (EditText) findViewById(R.id.edTieuDe);
        ThongTinSanPham = (EditText) findViewById(R.id.edThongTinSanPham);
        ThongTinNguoiBan = (TextView) findViewById(R.id.tvThongTinNguoiBan);
        Gia = (EditText) findViewById(R.id.edPrice);
        DangTin = (Button) findViewById(R.id.btnDangTin);
        Back = (Button) findViewById(R.id.btnBack);
        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        if(Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 69);
        }
    }


    private void setEvent() {
        DangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sp = new SanPham(TieuDe.getText().toString(), ThongTinSanPham.getText().toString(), link, "np001", Double.parseDouble(Gia.getText().toString()), new Date());
                PetShopFireBase.pushItem(sp, PetShopFireBase.TABLE_SAN_PHAM);
                Intent intent = new Intent(ThemSanPhamActivity.this, DanhSachSanPhamNguoiBanActivity.class);
                startActivity(intent);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemSanPhamActivity.this, DanhMucNguoiBanActivity.class);
                startActivity(intent);
            }
        });
        // chuyển qua thư viện
        ThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 96);
            }
        });
//        StringBuilder builder = new StringBuilder();
//        for (NguoiBan details : data) {
//            builder.append(details + "\n");
//        }
//        ThongTinNguoiBan.setText(builder.toString());
    }


    private void getFirebaseSanPham() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_NGUOI_BAN.status_data){
                    Object o = PetShopFireBase.search("id", id, PetShopFireBase.TABLE_NGUOI_BAN);
                    data = (List<NguoiBan>) o;
                }
                else handler.postDelayed(this, 1000);
            }
        });

    }

}
