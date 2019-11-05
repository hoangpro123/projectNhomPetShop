package tdc.edu.vn.project.Adapter;

//import com.example.selfcare.fragment.AppInfo;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import tdc.edu.vn.project.CpuInfo;
import tdc.edu.vn.project.R;
import tdc.edu.vn.project.Screen.HomeClient;
import tdc.edu.vn.project.Screen.ThongTinUser;
import tdc.edu.vn.project.UsageInfo;
import tdc.edu.vn.project.fragment_donHangNguoiMua;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    //private String listTab[] = {"Home","Danh mục", "Thông báo", "Cá nhân"};

    private Context context;
    private  String listTab[];

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        listTab = context.getResources().getStringArray(R.array.tablayout);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new HomeClient();
                break;
            case 1:
                fragment = new CpuInfo();
                break;
            case 2:
                fragment = new fragment_donHangNguoiMua();
                break;
            case 3:
                fragment = new ThongTinUser();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return listTab[position];
//    }
}
