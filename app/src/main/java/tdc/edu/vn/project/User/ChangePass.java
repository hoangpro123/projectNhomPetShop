package tdc.edu.vn.project.User;

import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.PetShopFireBase;
import tdc.edu.vn.project.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ChangePass extends AppCompatActivity {

    EditText editNewPass1, editOldPass, editNewPass;
    Button btnChangePass;

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
    }

    private void setEvent() {
        SharedPreferences sharedPreferences = getSharedPreferences("SaveId", Context.MODE_PRIVATE);
        final String id = sharedPreferences.getString("id", "");

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (PetShopFireBase.TABLE_NGUOI_MUA.status_data){
                    btnChangePass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            NguoiMua nm = ((ArrayList<NguoiMua>) PetShopFireBase.search("id", id, PetShopFireBase.TABLE_NGUOI_MUA)).get(0);
                            String oPass = nm.getPassword();
                            //editOldPass.setText(oPass);
                            if(editOldPass.getText().toString().equals(oPass)){
                                if(editNewPass1.getText().toString().equals(editNewPass.getText().toString())){
                                    nm.setPassword(editNewPass1.getText().toString());
                                    PetShopFireBase.pushItem(nm, PetShopFireBase.TABLE_NGUOI_MUA);
                                } else {
                                    Toast.makeText(ChangePass.this, "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
                                }
                               // Toast.makeText(ChangePass.this, "OK", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ChangePass.this, "Sai mật khẩu hiện tại", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

    }
    public void CheckPass(){
        SharedPreferences sharedPreferences = getSharedPreferences("SaveId", Context.MODE_PRIVATE);
        final String id = sharedPreferences.getString("id", "");
    }
}
