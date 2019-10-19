package tdc.edu.vn.project;

import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Model.NguoiMua;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class InforUserActivity extends AppCompatActivity {

    EditText editName, editEmail, editSDT, editAddress;
    Button btnSave, btnChangePass;
    RadioButton radNam, radNu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_user);

        setControl();
        setEvent();
    }

    private void setControl() {
        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSDT = (EditText) findViewById(R.id.editSDT);
        editAddress = (EditText) findViewById(R.id.editAddress);
        btnChangePass = (Button) findViewById(R.id.btnChangePass);
        btnSave = (Button) findViewById(R.id.btnChangePass);
        radNam = (RadioButton) findViewById(R.id.radNam);
        radNu = (RadioButton) findViewById(R.id.radNu);
    }

    private void setEvent() {
       Intent intent = this.getIntent();
       final String a = intent.getStringExtra("id");

        final Handler handler =  new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_NGUOI_MUA.status_data){
                    ArrayList<NguoiMua> data = (ArrayList<NguoiMua>)PetShopFireBase.TABLE_NGUOI_MUA.data;
                    editName.setText(data.get(0).getUsername());
                    editEmail.setText(a);
                }
                else handler.postDelayed(this, 1000);
            }
        });
    }
}
