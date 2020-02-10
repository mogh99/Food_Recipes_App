package com.example.bakingapp.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.bakingapp.Fragments.RecipesFragment;
import com.example.bakingapp.R;

//MainActivity.java will start the first fragment which include the different recipes_fragment
public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*RecipesFragment recipesFragment = new RecipesFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, recipesFragment)
                .commit();*/
    }
}
