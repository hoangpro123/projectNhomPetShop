package tdc.edu.vn.project.Screen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.PetShopSharedPreferences;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.User.ChangePass;

public class ChangePassNguoiBan extends AppCompatActivity {

    EditText editNewPass1, editOldPass, editNewPass;
    Button btnChangePass, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        setControl();
        setEvent();
    }

    private void setControl() {
        editNewPass = (EditText) findViewById(R.id.editNhapMKMoi);
        editOldPass = (EditText) findViewById(R.id.editNhapMKCu);
        editNewPass1 = (EditText) findViewById(R.id.editNhapLaiMKMoi);
        btnChangePass = (Button) findViewById(R.id.btnChangePass);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setEvent() {
        SharedPreferences sharedPreferences = getSharedPreferences(PetShopSharedPreferences.file_name, Context.MODE_PRIVATE);
        final String id = sharedPreferences.getString(PetShopSharedPreferences.idnb, null);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_NGUOI_MUA.status_data){
                    btnChangePass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!editOldPass.getText().toString().equals("") && !editNewPass1.getText().toString().equals("") && !editNewPass.getText().toString().equals("")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangePassNguoiBan.this);
                                builder.setTitle(getResources().getString(R.string.tb));
                                builder.setMessage(getResources().getString(R.string.bancomuonsuakhong));
                                builder.setCancelable(false);
                                builder.setPositiveButton(getResources().getString(R.string.ko), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(ChangePassNguoiBan.this, getResources().getString(R.string.koxoa), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNegativeButton(getResources().getString(R.string.co), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        NguoiBan nm = (NguoiBan) PetShopFireBase.findItem( id, PetShopFireBase.TABLE_NGUOI_BAN);
                                        String oPass = nm.getPassword();
                                        //editOldPass.setText(oPass);
                                        if(editOldPass.getText().toString().equals(oPass)){
                                            if(editNewPass1.getText().toString().equals(editNewPass.getText().toString())){
                                                nm.setPassword(editNewPass1.getText().toString());
                                                PetShopFireBase.pushItem(nm, PetShopFireBase.TABLE_NGUOI_BAN);
                                                Toast.makeText(ChangePassNguoiBan.this, "Doi mk thanh cong", Toast.LENGTH_SHORT).show();
                                                onBackPressed();
                                            } else {
                                                Toast.makeText(ChangePassNguoiBan.this, getResources().getString(R.string.mksai), Toast.LENGTH_SHORT).show();
                                            }
                                            // Toast.makeText(ChangePass.this, "OK", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ChangePassNguoiBan.this, getResources().getString(R.string.saimkhientai), Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                            else {
                                Toast.makeText(ChangePassNguoiBan.this, getResources().getString(R.string.nhaptt), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
                else handler.postDelayed(this, 1000);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}