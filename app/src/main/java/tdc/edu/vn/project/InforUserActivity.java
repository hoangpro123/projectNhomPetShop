package tdc.edu.vn.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Screen.ThongTinUser;
import tdc.edu.vn.project.User.ChangePass;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class InforUserActivity extends AppCompatActivity {

    EditText editName, editEmail, editSDT, editAddress;
    Button btnSave, btnChangePass, btnBack;
    RadioButton radGender;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_user);

        setControl();
        setEvent();
        LuuInfor();
        ChangePass();
        TroVe();
    }

    private void setControl() {
        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSDT = (EditText) findViewById(R.id.editSDT);
        editAddress = (EditText) findViewById(R.id.editAddress);
        btnChangePass = (Button) findViewById(R.id.btnChangePass);
        btnSave = (Button) findViewById(R.id.btnSave);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnBack = (Button) findViewById(R.id.btnBack);
    }

    private void setEvent() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_NGUOI_MUA.status_data) {
                    SharedPreferences sharedPreferences = getSharedPreferences("SaveId", Context.MODE_PRIVATE);
                    String id = sharedPreferences.getString("id", "");
                    NguoiMua nm = ((ArrayList<NguoiMua>) PetShopFireBase.search("id", id, PetShopFireBase.TABLE_NGUOI_MUA)).get(0);
                    editName.setText(nm.getName());
                    editEmail.setText(nm.getUsername());
                    editSDT.setText(nm.getPhone());
                    if (nm.getGender().equals("Nam")) {
                        radioGroup.check(R.id.radNam);
                    } else {
                        radioGroup.check(R.id.radNu);
                    }
                    editAddress.setText(nm.getAddress());

                } else handler.postDelayed(this, 1000);
            }
        });
    }

    public void LuuInfor() {
        SharedPreferences sharedPreferences = getSharedPreferences("SaveId", Context.MODE_PRIVATE);
        final String id = sharedPreferences.getString("id", "");
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_NGUOI_MUA.status_data) {
                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InforUserActivity.this);
                            builder.setTitle(getResources().getString(R.string.tb));
                            builder.setMessage(getResources().getString(R.string.bancomuonsuakhong));
                            builder.setCancelable(false);
                            builder.setPositiveButton(getResources().getString(R.string.ko), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(InforUserActivity.this, getResources().getString(R.string.koxoa), Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.setNegativeButton(getResources().getString(R.string.co), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    NguoiMua nm = ((ArrayList<NguoiMua>) PetShopFireBase.search("id", id, PetShopFireBase.TABLE_NGUOI_MUA)).get(0);
                                    nm.setName(editName.getText().toString());
                                    nm.setUsername(editEmail.getText().toString());
                                    nm.setAddress(editAddress.getText().toString());
                                    nm.setPhone(editSDT.getText().toString());
                                    int rad = radioGroup.getCheckedRadioButtonId();
                                    radGender = findViewById(rad);
                                    nm.setGender(radGender.getText().toString());
        //                            switch (radioGroup.getCheckedRadioButtonId()) {
        //                                case R.id.radNam:
        //                                    nm.setGender("Nam");
        //                                case R.id.radNu:
        //                                    nm.setGender("Nu");
        //                            }
                                    PetShopFireBase.pushItem(nm, PetShopFireBase.TABLE_NGUOI_MUA);
                                    Toast.makeText(InforUserActivity.this, getResources().getString(R.string.luutc), Toast.LENGTH_SHORT).show();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });
                }else handler.postDelayed(this, 1000);
            }
        });


    }
    public void ChangePass(){
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InforUserActivity.this, ChangePass.class);
                startActivity(intent);
            }
        });
    }

    public void TroVe(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InforUserActivity.this, ThongTinUser.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }


}
