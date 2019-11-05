package tdc.edu.vn.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import tdc.edu.vn.project.Adapter.FragmentAdapter;
import tdc.edu.vn.project.Model.NguoiMua;

public class FragmentMainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int imgResId[] = {
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_dashboard_black_24dp,
            R.drawable.ic_subtitles_black_24dp,
            R.drawable.ic_person_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        setControl();
        setEvent();
    }
    private void setEvent() {
       // setTitle("App Information");
//        Intent intent = this.getIntent();
//        final String a = intent.getStringExtra("id");
//        intent.putExtra("id", a);
        SharedPreferences sharedPreferences = getSharedPreferences("SaveId", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        NguoiMua nguoiMua = (NguoiMua) PetShopFireBase.findItem(id,PetShopFireBase.TABLE_NGUOI_MUA);
        

        FragmentManager manager = getSupportFragmentManager();


//        Bundle bundle = getIntent().getExtras();
//        String title = bundle.getString("id");
//        bundle.putString("id", title);
//        HomeClient homeClient = new HomeClient();
//        homeClient.setArguments(bundle);
//        final FragmentTransaction fragmentTransaction = manager.beginTransaction();
////        fragmentTransaction.add(R.id.fragment, homeClient);
////        fragmentTransaction.addToBackStack(title);
//        fragmentTransaction.commit();



       // FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(manager, this);
        //adapter.getItem(0);
        //fragmentTransaction.add(R.id.fragment, homeClient);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        setIconTablayout();

    }

    private void setControl() {
        viewPager = (ViewPager)findViewById(R.id.viewPager1);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout1);

    }

    private void setIconTablayout(){
        tabLayout.getTabAt(0).setIcon(imgResId[0]);
        tabLayout.getTabAt(1).setIcon(imgResId[1]);
        tabLayout.getTabAt(2).setIcon(imgResId[2]);
        tabLayout.getTabAt(3).setIcon(imgResId[3]);
        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
    }
}
