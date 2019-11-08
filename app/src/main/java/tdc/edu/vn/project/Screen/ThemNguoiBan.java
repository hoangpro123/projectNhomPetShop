package tdc.edu.vn.project.Screen;

import android.Manifest;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import tdc.edu.vn.project.Admin.AdminHome;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

public class ThemNguoiBan extends AppCompatActivity {
    EditText ho, ten, mail, sdt, pass, repass,diachi;
    RadioButton gender;
    Button btnback;
    ImageView img;
    StorageReference StoRef;
    RadioGroup group;
    Button tao, ch;
    String tenhinh="";
    public Uri imguri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_nguoi_ban);
        AnhXa();
        SetEvent();
        StoRef = FirebaseStorage.getInstance().getReference("Images");
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });
    }

    private void FileChooser(){
        Intent intent = new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    private void FileUpLoader(){
        StorageReference Ref = StoRef.child("logo"+System.currentTimeMillis());

        Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                tenhinh =  task.getResult().toString();
                                if (pass.getText().toString().equals(repass.getText().toString())){
                                    int re = group.getCheckedRadioButtonId();
                                    gender = findViewById(re);
                                    NguoiBan nguoiBan = new NguoiBan(ho.getText().toString(),mail.getText().toString(),pass.getText().toString(),sdt.getText().toString(),diachi.getText().toString(), tenhinh ,gender.getText().toString(),"");
                                    PetShopFireBase.pushItem(nguoiBan, PetShopFireBase.TABLE_NGUOI_BAN);
                                    Intent intent = new Intent(ThemNguoiBan.this, AdminHome.class);
                                    startActivity(intent);
                                    Toast.makeText(ThemNguoiBan.this, tenhinh, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(ThemNguoiBan.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                        Toast.makeText(ThemNguoiBan.this, "Image Upload Suusess", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getFileExtensionFromUrl(cr.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode== RESULT_OK && data != null && data.getData() != null){
            imguri = data.getData();
            img.setImageURI(imguri);
        }
    }

    public void SetEvent(){
        tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileUpLoader();

            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemNguoiBan.this, AdminHome.class);
                startActivity(intent);
            }
        });
    }
    public void AnhXa(){


        group = findViewById(R.id.grNgBan);

        ho = findViewById(R.id.edHNgBano);
        mail = findViewById(R.id.edMailNguoiBan);
        sdt = findViewById(R.id.edSDTNguoiBan);
        pass = findViewById(R.id.edPassNguoiBan);
        repass = findViewById(R.id.edRepassNgBan);
        diachi = findViewById(R.id.edDCNgBan);
        btnback = findViewById(R.id.btnBackad);
        img = findViewById(R.id.imgup);
        btnback = findViewById(R.id.btnBackad);
        tao = findViewById(R.id.btnTaoNgBan);
        ch = findViewById(R.id.choose);
    }
}
