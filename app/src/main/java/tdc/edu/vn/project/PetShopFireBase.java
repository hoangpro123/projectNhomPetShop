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
import com.google.firebase.database.Query;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

public class PetShopFireBase {
    public static DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    public static DatabaseReference TABLE_COUNT = db.child("count");
    public static DatabaseReference TABLE_LAST_ID = db.child("last_id");
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
    //
    public static Handler handler = new Handler();

    //
    public static void onTableLoaded(Class clss, String sMethod, eTable table){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (table.status_last_id && table.status_count && table.status_TABLE) {
                    try {
                        clss.getDeclaredMethod(sMethod).invoke(null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    public static void sortList(final String sField, final eTable table, final boolean inc) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (table.status_last_id && table.status_count && table.status_TABLE) {
                    final ArrayList<PetShopModel> data = (ArrayList<PetShopModel>) table.data;
                    if (data.size() < 2) return;
                    //
                    for (int i = 0; i < data.size() - 1; i++) {
                        int s = i;
                        for (int j = i + 1; j < data.size(); j++) {
                            Object oS = getValueField(sField, data.get(s));
                            Object oJ = getValueField(sField, data.get(j));
                            if(inc){
                                if (oS.getClass().equals(String.class)) {
                                    if(((String) oS).compareTo((String) oJ) > 0) s = j;
                                } else if (oS.getClass().equals(Integer.class)) {
                                    if(((Integer) oS).compareTo((Integer) oJ) > 0) s = j;
                                } else if (oS.getClass().equals(Float.class)) {
                                    if(((Float) oS).compareTo((Float) oJ) > 0) s = j;
                                } else if (oS.getClass().equals(Double.class)) {
                                    if(((Double) oS).compareTo((Double) oJ) > 0) s = j;
                                } else if (oS.getClass().equals(Date.class)) {
                                    if(((Date) oS).compareTo((Date) oJ) > 0) s = j;
                                }
                            }
                            else {
                                if (oS.getClass().equals(String.class)) {
                                    if(((String) oS).compareTo((String) oJ) < 0) s = j;
                                } else if (oS.getClass().equals(Integer.class)) {
                                    if(((Integer) oS).compareTo((Integer) oJ) < 0) s = j;
                                } else if (oS.getClass().equals(Float.class)) {
                                    if(((Float) oS).compareTo((Float) oJ) < 0) s = j;
                                } else if (oS.getClass().equals(Double.class)) {
                                    if(((Double) oS).compareTo((Double) oJ) < 0) s = j;
                                } else if (oS.getClass().equals(Date.class)) {
                                    if(((Date) oS).compareTo((Date) oJ) < 0) s = j;
                                }
                            }
                        }
                        if(i != s) Collections.swap(data, i, s);
                    }
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    public static void removeItem(final String id, final eTable table) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (table.status_last_id && table.status_count && table.status_TABLE) {
                    table.TABLE.child(id).setValue(null);
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    public static void pushItem(final PetShopModel item, final eTable table) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (table.status_last_id && table.status_count && table.status_TABLE) {
                    String id = item.getId();
                    if (id.equals("null"))
                        id = getNewID(table);
                    item.setId(id);
                    table.TABLE.child(id).setValue(item);
                } else handler.postDelayed(this, 1000);
            }
        });
    }

    public static void loadTable(final eTable table) {
        final ArrayList<PetShopModel> data = (ArrayList<PetShopModel>) table.data;
        data.clear();
        TABLE_LAST_ID.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getKey().equals(table.getName())) {
                    table.last_id = Integer.parseInt(dataSnapshot.getValue().toString());
                    table.setStatus_last_id(true);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getKey().equals(table.getName())) {
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
                if (dataSnapshot.getKey().equals(table.getName())) {
                    table.setCount(Integer.parseInt(dataSnapshot.getValue().toString()));
                    table.setStatus_count(true);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getKey().equals(table.getName())) {
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
                if (table.status_count) {
                    table.TABLE.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            PetShopModel item = (PetShopModel) dataSnapshot.getValue(table.cClass);
                            data.add(item);
                            if ((data.size() == table.count)) {
                                table.setStatus_TABLE(true);
                            }
                            if ((data.size() > table.count)) {
                                table.count = data.size();
                                TABLE_COUNT.child(table.getName()).setValue(table.count);
                                TABLE_LAST_ID.child(table.getName()).setValue(table.last_id + 1);
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
                            TABLE_COUNT.child(table.getName()).setValue(table.count - 1);
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else handler.postDelayed(this, 1000);
            }
        });
    }

    public static void initial() {
        TABLE_NGUOI_MUA.TABLE.child("null").setValue(new NguoiMua("NguoiMua", "nm001", "123456", "09123456789", "hcm", "link", "Nữ"));
        TABLE_DANH_GIA.TABLE.child("null").setValue(new DanhGia("nm001", "nb001", "ndsfs", (float) 3.5));
        TABLE_DANH_SACH_DEN.TABLE.child("null").setValue(new DanhSachDen("nm001", "nb001"));
        TABLE_DON_HANG.TABLE.child("null").setValue(new DonHang("nm001", "nb001", "ndsfs", 2, 1, (double) 120000));
        TABLE_GIAO_HANG.TABLE.child("null").setValue(new GiaoHang("nm001", new Date()));
        TABLE_GIO_HANG.TABLE.child("null").setValue(new GioHang("nm001", "nb001"));
        TABLE_HOA_HONG.TABLE.child("null").setValue(new HoaHong((float) 1, new Date(), (double) 563.333));
        TABLE_NGUOI_BAN.TABLE.child("null").setValue(new NguoiBan("nm001", "nb001", "ndsfs", "5", "abc", "abc", "Nam", "hh001"));
        TABLE_NGUOI_GIAO.TABLE.child("null").setValue(new NguoiGiao("nm001", "nb001", "ndsfs"));
        TABLE_QUAN_LY.TABLE.child("null").setValue(new QuanLy("nm001", "nb001", "ndsfs"));
        TABLE_SAN_PHAM.TABLE.child("null").setValue(new SanPham("nm001", "nb001", "ndsfs", "nb003", (double) 1930000, new Date()));
        //
        TABLE_LAST_ID.child(TABLE_NGUOI_MUA.name).setValue(1);
        TABLE_LAST_ID.child(TABLE_DANH_GIA.name).setValue(1);
        TABLE_LAST_ID.child(TABLE_DANH_SACH_DEN.name).setValue(1);
        TABLE_LAST_ID.child(TABLE_DON_HANG.name).setValue(1);
        TABLE_LAST_ID.child(TABLE_GIAO_HANG.name).setValue(1);
        TABLE_LAST_ID.child(TABLE_GIO_HANG.name).setValue(1);
        TABLE_LAST_ID.child(TABLE_HOA_HONG.name).setValue(1);
        TABLE_LAST_ID.child(TABLE_NGUOI_BAN.name).setValue(1);
        TABLE_LAST_ID.child(TABLE_NGUOI_GIAO.name).setValue(1);
        TABLE_LAST_ID.child(TABLE_QUAN_LY.name).setValue(1);
        TABLE_LAST_ID.child(TABLE_SAN_PHAM.name).setValue(1);
        //
        TABLE_COUNT.child(TABLE_NGUOI_MUA.name).setValue(1);
        TABLE_COUNT.child(TABLE_DANH_GIA.name).setValue(1);
        TABLE_COUNT.child(TABLE_DANH_SACH_DEN.name).setValue(1);
        TABLE_COUNT.child(TABLE_DON_HANG.name).setValue(1);
        TABLE_COUNT.child(TABLE_GIAO_HANG.name).setValue(1);
        TABLE_COUNT.child(TABLE_GIO_HANG.name).setValue(1);
        TABLE_COUNT.child(TABLE_HOA_HONG.name).setValue(1);
        TABLE_COUNT.child(TABLE_NGUOI_GIAO.name).setValue(1);
        TABLE_COUNT.child(TABLE_QUAN_LY.name).setValue(1);
        TABLE_COUNT.child(TABLE_SAN_PHAM.name).setValue(1);
    }

    //
    public static Object getValueField(String sField, PetShopModel item) {
        try {
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

    private static PetShopModel findItem(String id, eTable table) {
        final ArrayList<PetShopModel> data = (ArrayList<PetShopModel>) table.data;
        for (PetShopModel item : data) {
            if (item.getId().equals(id))
                return item;
        }
        return null;
    }

    //
    static {
        TABLE_NGUOI_MUA.TABLE = db.child(TABLE_NGUOI_MUA.name);
        TABLE_NGUOI_BAN.TABLE = db.child(TABLE_NGUOI_BAN.name);
        TABLE_DANH_GIA.TABLE = db.child(TABLE_DANH_GIA.name);
        TABLE_DANH_SACH_DEN.TABLE = db.child(TABLE_DANH_SACH_DEN.name);
        TABLE_DON_HANG.TABLE = db.child(TABLE_DON_HANG.name);
        TABLE_GIO_HANG.TABLE = db.child(TABLE_GIO_HANG.name);
        TABLE_HOA_HONG.TABLE = db.child(TABLE_HOA_HONG.name);
        TABLE_GIAO_HANG.TABLE = db.child(TABLE_GIAO_HANG.name);
        TABLE_NGUOI_GIAO.TABLE = db.child(TABLE_NGUOI_GIAO.name);
        TABLE_QUAN_LY.TABLE = db.child(TABLE_QUAN_LY.name);
        TABLE_SAN_PHAM.TABLE = db.child(TABLE_SAN_PHAM.name);
    }

    public enum eTable {
        NguoiMua("TABLE_NGUOI_MUA", "nm", 3, new ArrayList<tdc.edu.vn.project.Model.NguoiMua>(), tdc.edu.vn.project.Model.NguoiMua.class),
        NguoiBan("TABLE_NGUOI_BAN", "nb", 3, new ArrayList<tdc.edu.vn.project.Model.NguoiBan>(), tdc.edu.vn.project.Model.NguoiBan.class),
        DanhGia("TABLE_DANH_GIA", "dg", 3, new ArrayList<tdc.edu.vn.project.Model.DanhGia>(), tdc.edu.vn.project.Model.DanhGia.class),
        DanhSachDen("TABLE_DANH_SACH_DEN", "dsd", 3, new ArrayList<tdc.edu.vn.project.Model.DanhSachDen>(), tdc.edu.vn.project.Model.DanhSachDen.class),
        DonHang("TABLE_DON_HANG", "dh", 3, new ArrayList<tdc.edu.vn.project.Model.DonHang>(), tdc.edu.vn.project.Model.DonHang.class),
        GioHang("TABLE_GIO_HANG", "cart", 3, new ArrayList<tdc.edu.vn.project.Model.GioHang>(), tdc.edu.vn.project.Model.GioHang.class),
        HoaHong("TABLE_HOA_HONG", "hh", 3, new ArrayList<tdc.edu.vn.project.Model.HoaHong>(), tdc.edu.vn.project.Model.HoaHong.class),
        GiaoHang("TABLE_GIAO_HANG", "gh", 3, new ArrayList<tdc.edu.vn.project.Model.GiaoHang>(), tdc.edu.vn.project.Model.GiaoHang.class),
        NguoiGiao("TABLE_NGUOI_GIAO", "ng", 3, new ArrayList<tdc.edu.vn.project.Model.NguoiGiao>(), tdc.edu.vn.project.Model.NguoiGiao.class),
        QuanLy("TABLE_QUAN_LY", "ql", 3, new ArrayList<tdc.edu.vn.project.Model.QuanLy>(), tdc.edu.vn.project.Model.QuanLy.class),
        SanPham("TABLE_SAN_PHAM", "sp", 3, new ArrayList<tdc.edu.vn.project.Model.SanPham>(), tdc.edu.vn.project.Model.SanPham.class);

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
