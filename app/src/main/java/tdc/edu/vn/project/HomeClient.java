package tdc.edu.vn.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.util.HashMap;

import tdc.edu.vn.project.Adapter.RecyclerViewAdapter;
import tdc.edu.vn.project.Model.SanPham;
import tdc.edu.vn.project.Screen.ChiTietThuCung;
import tdc.edu.vn.project.Screen.GioHang;

public class HomeClient extends Fragment
        implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {
    Button btnCart;
    private SliderLayout sliderLayout ;

    private HashMap<String, String> HashMapForURL ;

    private HashMap<String, Integer> HashMapForLocalRes ;

    private ArrayList<SanPham> listPet ;

    private RecyclerView myrv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_client, null);

                myrv = view.findViewById(R.id.recyclerview);
        PetShopFireBase.loadTable(PetShopFireBase.TABLE_SAN_PHAM);
        listPet = new ArrayList<>();
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_SAN_PHAM.status_last_id && PetShopFireBase.TABLE_SAN_PHAM.status_count && PetShopFireBase.TABLE_SAN_PHAM.status_TABLE){
                    ArrayList<SanPham> data = (ArrayList<SanPham>)PetShopFireBase.TABLE_SAN_PHAM.data;
                    listPet = data;
                    RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getActivity(),listPet);


                    // RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(HomeClien, 2);
                    myrv.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    myrv.setAdapter(myAdapter);

                    Log.d("ggg", data.size() + "");
                }
                else handler.postDelayed(this, 1000);
            }
        });

        View view1 = inflater.inflate(R.layout.layout_giohang, null);
        //KhoiTao();
        btnCart = (Button)view.findViewById(R.id.shopingcart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GioHang.class);
                startActivity(intent);
            }
        });

        sliderLayout = (SliderLayout)view.findViewById(R.id.slider);

        //Call this method if you want to add images from URL .
        //AddImagesUrlOnline();

        //Call this method to add images from local drawable folder .
        AddImageUrlFormLocalRes();

        //Call this method to stop automatic sliding.
        //sliderLayout.stopAutoCycle();

        for(String name : HashMapForLocalRes.keySet()){

            TextSliderView textSliderView = new TextSliderView(getActivity());

            textSliderView
                    .description(name)
                    .image(HashMapForLocalRes.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(HomeClient.this);

        return view;
    }

    @Override
    public void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        //Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImagesUrlOnline(){

        HashMapForURL = new HashMap<String, String>();

        HashMapForURL.put("Hinh1", "https://dogily.vn/wp-content/uploads/2019/09/trai-meo-anh-long-ngan-tai-chau-au.jpg");
        HashMapForURL.put("Hinh2", "https://dogily.vn/wp-content/uploads/2019/09/trai-meo-anh-long-ngan-tai-chau-au.jpg");
        HashMapForURL.put("Hinh3", "https://dogily.vn/wp-content/uploads/2019/09/trai-meo-anh-long-ngan-tai-chau-au.jpg");
        HashMapForURL.put("Hinh4", "https://dogily.vn/wp-content/uploads/2019/09/trai-meo-anh-long-ngan-tai-chau-au.jpg");
        HashMapForURL.put("Hinh5", "https://dogily.vn/wp-content/uploads/2019/09/trai-meo-anh-long-ngan-tai-chau-au.jpg");
    }

    public void AddImageUrlFormLocalRes(){

        HashMapForLocalRes = new HashMap<String, Integer>();
        HashMapForLocalRes.put("Hinh1", R.drawable.meo1);
        HashMapForLocalRes.put("Hinh2", R.drawable.meo2);
        HashMapForLocalRes.put("Hinh3", R.drawable.meo3);
        HashMapForLocalRes.put("Hinh4", R.drawable.meo4);
        HashMapForLocalRes.put("Hinh5", R.drawable.meo5);

    }



//    public void KhoiTao() {
//        final RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview);
//        PetShopFireBase.loadTable(PetShopFireBase.TABLE_SAN_PHAM);
//        listPet = new ArrayList<>();
//        final Handler handler = new Handler();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (PetShopFireBase.TABLE_SAN_PHAM.status_last_id && PetShopFireBase.TABLE_SAN_PHAM.status_count && PetShopFireBase.TABLE_SAN_PHAM.status_TABLE) {
//                    ArrayList<SanPham> data = (ArrayList<SanPham>) PetShopFireBase.TABLE_SAN_PHAM.data;
//                    listPet = data;
//                    RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(HomeClient.this, listPet);
//                    myrv.setLayoutManager(new GridLayoutManager(HomeClient.this, 2));
//                    myrv.setAdapter(myAdapter);
//
//                    Log.d("ggg", data.size() + "");
//                } else handler.postDelayed(this, 1000);
//            }
//        });
//
//    }


        /*listPet.add(new Pet("The Wild Robot","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("Maria Semples","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Martian","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("He Died with...","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Wild Robot","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("Maria Semples","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Martian","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("He Died with...","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Wild Robot","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("Maria Semples","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Martian","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("He Died with...","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book",R.drawable.logo));
*/


}
