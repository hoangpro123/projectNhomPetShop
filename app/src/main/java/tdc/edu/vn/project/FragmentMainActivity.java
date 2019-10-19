package tdc.edu.vn.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import tdc.edu.vn.project.Adapter.FragmentAdapter;
import tdc.edu.vn.project.User.ThongTinUser;

public class FragmentMainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView textView;

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
        FragmentManager manager = getSupportFragmentManager();




        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("id");
        bundle.putString("id", title);
        HomeClient homeClient = new HomeClient();
        homeClient.setArguments(bundle);
        final FragmentTransaction fragmentTransaction = manager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment, homeClient);
//        fragmentTransaction.addToBackStack(title);
        fragmentTransaction.commit();
        textView.setText(title);



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
        textView = (TextView) findViewById(R.id.txt);
    }
}
