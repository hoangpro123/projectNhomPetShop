package tdc.edu.vn.project.Adapter;

//import com.example.selfcare.fragment.AppInfo;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import tdc.edu.vn.project.CpuInfo;
import tdc.edu.vn.project.Screen.HomeClient;
import tdc.edu.vn.project.UsageInfo;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"Home","Danh mục", "Thông báo", "Cá nhân"};

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
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
                fragment = new UsageInfo();
                break;
            case 3:

                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
