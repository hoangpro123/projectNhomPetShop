package tdc.edu.vn.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


public class HomeClient extends AppCompatActivity {

    List<Pet> listPet ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_client);

        listPet = new ArrayList<>();
        listPet.add(new Pet("The Vegitarian","500000","Description book",R.drawable.logo));
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
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Wild Robot","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("Maria Semples","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Martian","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("He Died with...","Categorie Book","Description book",R.drawable.logo));
        listPet.add(new Pet("The Vegitarian","Categorie Book","Description book",R.drawable.logo));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,listPet);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

    }
}
