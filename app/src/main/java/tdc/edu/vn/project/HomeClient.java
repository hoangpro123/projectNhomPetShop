package tdc.edu.vn.project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.util.HashMap;

import tdc.edu.vn.project.Model.NguoiMua;
import tdc.edu.vn.project.Model.SanPham;

public class HomeClient extends AppCompatActivity
        implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout ;

    HashMap<String, String> HashMapForURL ;

    HashMap<String, Integer> HashMapForLocalRes ;

    List<Pet> listPet ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_client);
        KhoiTao();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        //Call this method if you want to add images from URL .
        //AddImagesUrlOnline();

        //Call this method to add images from local drawable folder .
        AddImageUrlFormLocalRes();

        //Call this method to stop automatic sliding.
        //sliderLayout.stopAutoCycle();

        for(String name : HashMapForLocalRes.keySet()){

            TextSliderView textSliderView = new TextSliderView(HomeClient.this);

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
    }

    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
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
        HashMapForLocalRes.put("Hinh1", R.drawable.logo);
        HashMapForLocalRes.put("Hinh2", R.drawable.logo);
        HashMapForLocalRes.put("Hinh3", R.drawable.logo);
        HashMapForLocalRes.put("Hinh4", R.drawable.logo);
        HashMapForLocalRes.put("Hinh5", R.drawable.logo);

    }

    public void KhoiTao(){
        PetShopFireBase.loadTable(PetShopFireBase.TABLE_SAN_PHAM);
        listPet = new ArrayList<>();
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(PetShopFireBase.TABLE_SAN_PHAM.status_last_id && PetShopFireBase.TABLE_SAN_PHAM.status_count && PetShopFireBase.TABLE_SAN_PHAM.status_TABLE){
                    ArrayList<SanPham> data = (ArrayList<SanPham>)PetShopFireBase.TABLE_SAN_PHAM.data;
                    for (int i = 0; i < data.size(); i++){
                        listPet.add(new Pet(data.get(i).getName(),data.get(i).getPrice(),data.get(i).getDescription(),R.drawable.logo));
                    }

                    Log.d("ggg", data.size() + "");
                }
                else handler.postDelayed(this, 1000);
            }
        });



        listPet.add(new Pet("The Vegitarian","500000","Description book",R.drawable.logo));
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
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,listPet);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);
    }

}
