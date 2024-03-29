package tdc.edu.vn.project;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.otto.Bus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import tdc.edu.vn.project.Model.DanhGia;
import tdc.edu.vn.project.Model.DanhSachDen;
import tdc.edu.vn.project.Model.DonHang;
import tdc.edu.vn.project.Model.GiaoHang;
import tdc.edu.vn.project.Model.GioHang;
import tdc.edu.vn.project.Model.HoaHong;
import tdc.edu.vn.project.Model.NguoiBan;
import tdc.edu.vn.project.Model.NguoiGiao;
import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.PetShopModel;
import tdc.edu.vn.project.Model.QuanLy;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.Model.TinhTrangDonHang;

public class PetShopFireBase {
    public static Bus bus = new Bus();

    public static StorageReference fireBaseStorage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://chuyendedidongnhom3.appspot.com");
    public static DatabaseReference fireBase = FirebaseDatabase.getInstance().getReference();
    //
    public static eTable TABLE_NGUOI_MUA = eTable.NguoiMua;
    public static eTable TABLE_NGUOI_BAN = eTable.NguoiBan;
    public static eTable TABLE_DANH_GIA = eTable.DanhGia;
    public static eTable TABLE_DANH_SACH_DEN = eTable.DanhSachDen;
    public static eTable TABLE_DON_HANG = eTable.DonHang;
    public static eTable TABLE_GIO_HANG = eTable.GioHang;
    public static eTable TABLE_HOA_HONG = eTable.HoaHong;
    public static eTable TABLE_GIAO_HANG = eTable.GiaoHang;
    public static eTable TABLE_NGUOI_GIAO = eTable.NguoiGiao;
    public static eTable TABLE_QUAN_LY = eTable.QuanLy;
    public static eTable TABLE_SAN_PHAM = eTable.SanPham;
    public static eTable TABLE_YEU_CAU_CHINH_SUA = eTable.YeuCauChinhSua;
    public static eTable TABLE_TINH_TRANG_DON_HANG = eTable.TinhTrangDonHang;


    public static Object search(String sField, Object value, eTable table) {
        ArrayList<PetShopModel> results = new ArrayList<>();
        ArrayList<PetShopModel> data = (ArrayList<PetShopModel>) table.getData();
        for (PetShopModel item : data) {
            try {
                Object v;
                if (sField.equals("id")) v = item.getId();
                else{
                    Field field = item.getClass().getDeclaredField(sField);
                    field.setAccessible(true);
                    v = field.get(item);
                }
                boolean b = v.equals(value);
                if (b) results.add(item);
                if (!b && v instanceof Date) {
                    Date d1 = (Date) value;
                    Date d2 = (Date) v;
                    if (d1.getYear() == d2.getYear() && d1.getMonth() == d2.getMonth() && d1.getDate() == d2.getDate() && d1.getHours() == d2.getHours() && d1.getMinutes() == d2.getMinutes() && d1.getSeconds() == d2.getSeconds())
                        results.add(item);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static void sortList(final String sField, final eTable table, final boolean inc) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (table.status_last_id && table.status_data) {
                    final ArrayList<PetShopModel> data = (ArrayList<PetShopModel>) table.data;
                    if (data.size() < 2) return;
                    //
                    for (int i = 0; i < data.size() - 1; i++) {
                        int s = i;
                        for (int j = i + 1; j < data.size(); j++) {
                            Object oS = getValueField(sField, data.get(s));
                            Object oJ = getValueField(sField, data.get(j));
                            if (inc) {
                                if (compare(oS, oJ)) s = j;
                            } else {
                                if (!compare(oS, oJ)) s = j;
                            }
                        }
                        if (i != s) Collections.swap(data, i, s);
                    }
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    public static void removeItem(final String id, final eTable table) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (table.status_last_id && table.status_data) {
                    table.TABLE_DATA.child(id).setValue(null);
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    public static void pushItems(final Object objArr, final eTable table){
        ArrayList<PetShopModel> list = (ArrayList<PetShopModel>) objArr;
        if(list.isEmpty())return;
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(!table.isPushing()){
                    pushItem(list.get(0),table);
                    list.remove(0);
                    pushItems(list,table);
                }else handler.postDelayed(this,1000);
            }
        });
    }

    public static void pushItem(final PetShopModel item, final eTable table) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (table.status_last_id && table.status_data) {
                    String id = item.getId();
                    if (id.equals("null"))
                        id = getNewID(table);
                    item.setId(id);
                    table.setPushing(true);
                    table.TABLE_DATA.child(id).setValue(item);
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    //
    private static Boolean compare(Object o1, Object o2) {
        if (o1 instanceof String) return ((String) o1).compareTo((String) o2) > 0;
        if (o1 instanceof Integer) return ((Integer) o1).compareTo((Integer) o2) > 0;
        if (o1 instanceof Float) return ((Float) o1).compareTo((Float) o2) > 0;
        if (o1 instanceof Double) return ((Double) o1).compareTo((Double) o2) > 0;
        if (o1 instanceof Date) return ((Date) o1).compareTo((Date) o2) > 0;
        return false;
    }

    private static Object getValueField(String sField, PetShopModel item) {
        try {
            if(sField.equals("id")) return item.getId();
            Field field = item.getClass().getDeclaredField(sField);
            field.setAccessible(true);
            return field.get(item);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getNewID(eTable table) {
        String key = table.getKey();
        int id_length = 0;
        for (int i = table.last_id + 1; i > 0; i /= 10) {
            id_length++;
        }
        if (id_length == 0) id_length = 1;
        for (int i = table.max_length - id_length; i > 0; i--) {
            key += "0";
        }
        key += String.valueOf(table.last_id + 1);
        return key;
    }

    public static PetShopModel findItem(String id, eTable table) {
        final ArrayList<PetShopModel> data = (ArrayList<PetShopModel>) table.data;
        for (PetShopModel item : data) {
            if (item.getId().equals(id))
                return item;
        }
        return null;
    }

    static {
        loadTable(TABLE_NGUOI_MUA);
        loadTable(TABLE_NGUOI_BAN);
        loadTable(TABLE_DANH_GIA);
        loadTable(TABLE_DANH_SACH_DEN);
        loadTable(TABLE_DON_HANG);
        loadTable(TABLE_GIO_HANG);
        loadTable(TABLE_HOA_HONG);
        loadTable(TABLE_GIAO_HANG);
        loadTable(TABLE_NGUOI_GIAO);
        loadTable(TABLE_QUAN_LY);
        loadTable(TABLE_SAN_PHAM);
        loadTable(TABLE_YEU_CAU_CHINH_SUA);
        loadTable(TABLE_TINH_TRANG_DON_HANG);
    }

    private static void loadTable(final eTable table) {
        if (table.status_data || table.status_last_id) return;
        //
        final ArrayList<PetShopModel> data = (ArrayList<PetShopModel>) table.data;
        table.TABLE_DATA.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final long[] count = {dataSnapshot.getChildrenCount()};
                table.TABLE_DATA.removeEventListener(this);
                table.TABLE_DATA.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        data.add((PetShopModel) dataSnapshot.getValue(table.getcClass()));
                        if (data.size() == count[0]) table.setStatus_data(true);
                        if (data.size() > count[0]) {
                            table.TABLE_LAST_ID.setValue(table.last_id + 1);
                            count[0] = data.size();
                            bus.post(table.getName());
                            table.setPushing(false);
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        data.remove(findItem(dataSnapshot.getKey(), table));
                        data.add((PetShopModel) dataSnapshot.getValue(table.cClass));
                        bus.post(table.getName());
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        data.remove(findItem(dataSnapshot.getKey(), table));
                        count[0] = data.size();
                        bus.post(table.getName());
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //
        table.TABLE_LAST_ID.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                table.setLast_id(Integer.parseInt(dataSnapshot.getValue().toString()));
                table.setStatus_last_id(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public enum eTable {
        NguoiMua("TABLE_NGUOI_MUA", "nm", 3, NguoiMua.class),
        NguoiBan("TABLE_NGUOI_BAN", "nb", 3, NguoiBan.class),
        DanhGia("TABLE_DANH_GIA", "dg", 3, DanhGia.class),
        DanhSachDen("TABLE_DANH_SACH_DEN", "dsd", 3, DanhSachDen.class),
        DonHang("TABLE_DON_HANG", "dh", 3, DonHang.class),
        GioHang("TABLE_GIO_HANG", "cart", 3, GioHang.class),
        HoaHong("TABLE_HOA_HONG", "hh", 3, HoaHong.class),
        GiaoHang("TABLE_GIAO_HANG", "gh", 3, GiaoHang.class),
        NguoiGiao("TABLE_NGUOI_GIAO", "ng", 3, NguoiGiao.class),
        QuanLy("TABLE_QUAN_LY", "ql", 3, QuanLy.class),
        SanPham("TABLE_SAN_PHAM", "sp", 3, SanPham.class),
        YeuCauChinhSua("TABLE_YEU_CAU_CHINH_SUA", "yccs", 3, NguoiBan.class),
        TinhTrangDonHang("TABLE_TINH_TRANG_DON_HANG", "ttdh", 3, TinhTrangDonHang.class);

        public String name, key;
        public int last_id, max_length;
        public Object data;
        public DatabaseReference TABLE_DATA, TABLE_LAST_ID;
        public boolean status_last_id, status_data, isPushing;
        Class cClass;

        eTable(String name, String key, int max_length, Class cClass) {
            this.name = name;
            this.key = key;
            this.max_length = max_length;
            this.cClass = cClass;

            data = new ArrayList<PetShopModel>();
            last_id = 0;
            status_last_id = status_data = isPushing = false;

            TABLE_DATA = fireBase.child(name);
            TABLE_LAST_ID = fireBase.child("last_id").child(name);
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

        public int getMax_length() {
            return max_length;
        }

        public void setMax_length(int max_length) {
            this.max_length = max_length;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public DatabaseReference getTABLE_DATA() {
            return TABLE_DATA;
        }

        public void setTABLE_DATA(DatabaseReference TABLE_DATA) {
            this.TABLE_DATA = TABLE_DATA;
        }

        public DatabaseReference getTABLE_LAST_ID() {
            return TABLE_LAST_ID;
        }

        public void setTABLE_LAST_ID(DatabaseReference TABLE_LAST_ID) {
            this.TABLE_LAST_ID = TABLE_LAST_ID;
        }

        public boolean isStatus_last_id() {
            return status_last_id;
        }

        public void setStatus_last_id(boolean status_last_id) {
            this.status_last_id = status_last_id;
        }

        public boolean isStatus_data() {
            return status_data;
        }

        public void setStatus_data(boolean status_data) {
            this.status_data = status_data;
        }

        public boolean isPushing() {
            return isPushing;
        }

        public void setPushing(boolean pushing) {
            isPushing = pushing;
        }

        public Class getcClass() {
            return cClass;
        }

        public void setcClass(Class cClass) {
            this.cClass = cClass;
        }
    }
}