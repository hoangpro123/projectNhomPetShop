package tdc.edu.vn.project;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class db {
    public static DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    public static DatabaseReference TABLE_NGUOI_MUA = db.child("nguoimua");
    private static int id_nm_max = 0;
    //
    public static void addNguoiMua(NguoiMua nm){
        TABLE_NGUOI_MUA.child(getNguoiMuaID()).setValue(nm);
        db.child("id_nm").child("0").setValue(String.valueOf(id_nm_max + 1));
    }
    public static String getNguoiMuaID(){
        String key = "nm";
        int id_number_length = 1;
        for(int i = id_nm_max; i > 0; i /= 10){
            id_number_length++;
        }
        for(int i = 3 - id_number_length; i > 0; i--){
            key += "0";
        }
        key += String.valueOf(id_nm_max);
        return key;
    }
    public static void loadNguoiMua(){
        db.child("id_nm").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                id_nm_max = Integer.parseInt(dataSnapshot.getValue().toString());
                Log.d("lll", "onChildAdded: ");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        TABLE_NGUOI_MUA.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                NguoiMua.list_nguoi_mua.add(dataSnapshot.getValue(NguoiMua.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
