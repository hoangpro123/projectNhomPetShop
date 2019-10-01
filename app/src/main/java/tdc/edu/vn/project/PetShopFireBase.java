package tdc.edu.vn.project;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.PetShopModel;

public class PetShopFireBase {
    public static DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    public static DatabaseReference TABLE_COUNT = db.child("count");
    public static DatabaseReference TABLE_LAST_ID = db.child("last_id");
    //
    public static eTable nm = eTable.NguoiMua;
    public static eTable nb = eTable.NguoiBan;
    public static eTable dg = eTable.DanhGia;
    public static eTable dsd = eTable.DanhSachDen;
    public static eTable dh = eTable.DonHang;
    public static eTable cart = eTable.GioHang;
    public static eTable hh = eTable.HoaHong;
    public static eTable gh = eTable.GiaoHang;
    public static eTable ng = eTable.NguoiGiao;
    public static eTable ql = eTable.QuanLy;
    public static eTable sp = eTable.SanPham;
    //
    public static Handler handler = new Handler();
    //
    static {
        nm.TABLE = db.child(nm.name);
        nb.TABLE = db.child(nb.name);
        dg.TABLE = db.child(dg.name);
        dsd.TABLE = db.child(dsd.name);
        dh.TABLE = db.child(dh.name);
        cart.TABLE = db.child(cart.name);
        hh.TABLE = db.child(hh.name);
        gh.TABLE = db.child(gh.name);
        ng.TABLE = db.child(ng.name);
        ql.TABLE = db.child(ql.name);
        sp.TABLE = db.child(sp.name);
    }
    //

    public static void removeItem(final String id, final eTable table){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(table.status_last_id && table.status_count && table.status_TABLE){
                    table.TABLE.child(id).setValue(null);
                }
                else handler.postDelayed(this, 1000);
            }
        });
    }
    public static void pushItem(final PetShopModel item, final eTable table){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(table.status_last_id && table.status_count && table.status_TABLE){
                    String id = item.getId();
                    if(item.getId() == null)
                        id = getNewID(table);
                    item.setId(id);
                    table.TABLE.child(id).setValue(item);
                }
                else handler.postDelayed(this, 1000);
            }
        });
    }
    public static void loadTable(final eTable table){
        final ArrayList<PetShopModel> data = (ArrayList<PetShopModel>) table.data;
        data.clear();
        TABLE_LAST_ID.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.getKey().equals(table.getKey())){
                    table.last_id = Integer.parseInt(dataSnapshot.getValue().toString());
                    table.setStatus_last_id(true);
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.getKey().equals(table.getKey())){
                    table.last_id = Integer.parseInt(dataSnapshot.getValue().toString());
                    table.setStatus_last_id(true);
                }
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
        TABLE_COUNT.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.getKey().equals(table.getKey())){
                    table.setCount(Integer.parseInt(dataSnapshot.getValue().toString()));
                    table.setStatus_count(true);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.getKey().equals(table.getKey())){
                    table.setCount(Integer.parseInt(dataSnapshot.getValue().toString()));
                    table.setStatus_count(true);
                }
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
                if(table.status_count){
                    table.TABLE.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            PetShopModel item = (PetShopModel) dataSnapshot.getValue(table.cClass);
                            data.add(item);
                            if((data.size() == table.count)){
                                table.setStatus_TABLE(true);
                            }
                            if((data.size() > table.count)){
                                table.count = data.size();
                                TABLE_COUNT.child(table.key).setValue(table.count);
                                TABLE_LAST_ID.child(table.key).setValue(table.last_id + 1);
                            }
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            PetShopModel item = (PetShopModel) dataSnapshot.getValue(table.cClass);
                            data.remove(findItem(dataSnapshot.getKey(), table));
                            data.add(item);
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                            data.remove(findItem(dataSnapshot.getKey(), table));
                            TABLE_COUNT.child(table.key).setValue(table.count - 1);
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else handler.postDelayed(this, 1000);
            }
        });
    }
    //
    private static String getNewID(eTable table){
        String key = table.getKey();
        int id_length = 0;
        for(int i = table.last_id + 1; i > 0; i /= 10){
            id_length++;
        }
        if(id_length == 0) id_length = 1;
        for(int i = table.max_length - id_length; i > 0; i--){
            key += "0";
        }
        key += String.valueOf(table.last_id + 1);
        return key;
    }
    private static PetShopModel findItem(String id, eTable table){
        final ArrayList<PetShopModel> data = (ArrayList<PetShopModel>) table.data;
        for(PetShopModel item: data){
            if(item.getId().equals(id))
                return item;
        }
        return null;
    }
    public enum eTable {
        NguoiMua   ("TABLE_NGUOI_MUA",     "nm",   3, new ArrayList<tdc.edu.vn.project.Model.NguoiMua>(), tdc.edu.vn.project.Model.NguoiMua.class),
        NguoiBan   ("TABLE_NGUOI_BAN",     "nb",   3, new ArrayList<tdc.edu.vn.project.Model.NguoiBan>(), tdc.edu.vn.project.Model.NguoiBan.class),
        DanhGia    ("TABLE_DANH_GIA",      "dg",   3, new ArrayList<tdc.edu.vn.project.Model.DanhGia>(), tdc.edu.vn.project.Model.DanhGia.class),
        DanhSachDen("TABLE_DANH_SACH_DEN", "dsd",  3, new ArrayList<tdc.edu.vn.project.Model.DanhSachDen>(), tdc.edu.vn.project.Model.DanhSachDen.class),
        DonHang    ("TABLE_DON_HANG",      "dh",   3, new ArrayList<tdc.edu.vn.project.Model.DonHang>(), tdc.edu.vn.project.Model.DonHang.class),
        GioHang    ("TABLE_GIO_HANG",      "cart", 3, new ArrayList<tdc.edu.vn.project.Model.GioHang>(), tdc.edu.vn.project.Model.GioHang.class),
        HoaHong    ("TABLE_HOA_HONG",      "hh",   3, new ArrayList<tdc.edu.vn.project.Model.HoaHong>(), tdc.edu.vn.project.Model.HoaHong.class),
        GiaoHang   ("TABLE_GIAO_HANG",     "gh",   3, new ArrayList<tdc.edu.vn.project.Model.GiaoHang>(), tdc.edu.vn.project.Model.GiaoHang.class),
        NguoiGiao  ("TABLE_NGUOI_GIAO",    "ng",   3, new ArrayList<tdc.edu.vn.project.Model.NguoiGiao>(), tdc.edu.vn.project.Model.NguoiGiao.class),
        QuanLy     ("TABLE_QUAN_LY",       "ql",   3, new ArrayList<tdc.edu.vn.project.Model.QuanLy>(), tdc.edu.vn.project.Model.QuanLy.class),
        SanPham    ("TABLE_SAN_PHAM",      "sp",   3, new ArrayList<tdc.edu.vn.project.Model.SanPham>(), tdc.edu.vn.project.Model.SanPham.class);

        public String name, key;
        public int last_id, count, max_length;
        public Object data;
        public DatabaseReference TABLE;
        public boolean status_last_id, status_count, status_TABLE;
        Class cClass;

        eTable(String name, String key, int max_length, Object data, Class cClass) {
            this.name = name;
            this.key = key;
            this.max_length = max_length;
            this.data = data;
            this.cClass = cClass;

            this.last_id = 0;
            this.count = 0;
            this.status_last_id = this.status_count = this.status_TABLE = false;
        }

        public Class getcClass() {
            return cClass;
        }

        public void setcClass(Class cClass) {
            this.cClass = cClass;
        }

        public boolean isStatus_last_id() {
            return status_last_id;
        }

        public void setStatus_last_id(boolean status_last_id) {
            this.status_last_id = status_last_id;
        }

        public boolean isStatus_count() {
            return status_count;
        }

        public void setStatus_count(boolean status_count) {
            this.status_count = status_count;
        }

        public boolean isStatus_TABLE() {
            return status_TABLE;
        }

        public void setStatus_TABLE(boolean status_TABLE) {
            this.status_TABLE = status_TABLE;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public DatabaseReference getTABLE() {
            return TABLE;
        }

        public void setTABLE(DatabaseReference TABLE) {
            this.TABLE = TABLE;
        }

        public int getMax_length() {
            return max_length;
        }

        public void setMax_length(int max_length) {
            this.max_length = max_length;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getLast_id() {
            return last_id;
        }

        public void setLast_id(int last_id) {
            this.last_id = last_id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
