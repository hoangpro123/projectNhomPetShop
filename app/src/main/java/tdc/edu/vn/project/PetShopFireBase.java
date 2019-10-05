package tdc.edu.vn.project;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import tdc.edu.vn.project.Model.NguoiMua;

public class PetShopFireBase {
    public static Handler handler = new Handler();
    //
    public static HashMap<String, Boolean> Loaded = new HashMap<>();
    public static int last_nm = 0;
    public static int count_nm = 0;

    public static DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    public static DatabaseReference TABLE_NGUOI_MUA = db.child("nguoimua");


    public static boolean isReady = false;
    //
    static {
        Loaded.put("last_nm", false);
        Loaded.put("count_nm", false);
        Loaded.put("table_nm", false);

    }
    //
    public static void addNguoiMua(NguoiMua nm){
        String id = getNguoiMuaID();
        nm.setId(id);
        TABLE_NGUOI_MUA.child(id).setValue(nm);
        db.child("last_id").child("nm").setValue(last_nm);
    }
    private static String getNguoiMuaID(){
        String key = "nm";
        int id_length = 0;
        for(int i = ++last_nm; i > 0; i /= 10){
            id_length++;
        }
        if(id_length == 0) id_length = 1;
        for(int i = 3 - id_length; i > 0; i--){
            key += "0";
        }
        key += String.valueOf(last_nm);
        return key;
    }
    public static void loadNguoiMua(){
        db.child("last_id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                switch (dataSnapshot.getKey()){
                    case "nm":
                        last_nm = Integer.parseInt(dataSnapshot.getValue().toString());
                        Loaded.put("last_nm", true);
                        break;
                    case"nb":
                        break;
                }

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
        db.child("count").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                switch (dataSnapshot.getKey()){
                    case "nm":
                        count_nm = Integer.parseInt(dataSnapshot.getValue().toString());
                        Loaded.put("count_nm", true);
                        break;
                    case "nb":
                        break;
                }
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
        //
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(Loaded.get("count_nm")){
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
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(NguoiMua.list_nguoi_mua.size() == count_nm){
                                Loaded.put("table_nm", true);
                            }
                            else handler.postDelayed(this, 1000);
                        }
                    });
                }
                else handler.postDelayed(this, 1000);
            }
        });
        handler.post(new Runnable() {
            @Override
            public void run() {
                Set<Map.Entry<String, Boolean>> setLoaded = Loaded.entrySet();
                for(Map.Entry<String, Boolean> item: setLoaded){
                    if(!item.getValue()){
                        handler.postDelayed(this, 1000);
                        return;
                    }
                }
                isReady = true;
            }
        });

        //

    }

}
