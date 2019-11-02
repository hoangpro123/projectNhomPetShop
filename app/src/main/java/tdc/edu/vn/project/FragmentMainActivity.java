package tdc.edu.vn.project;

import android.content.Context;
import android.content.SharedPreferences;
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

    }

    private void setControl() {
        viewPager = (ViewPager)findViewById(R.id.viewPager1);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout1);

    }
}
