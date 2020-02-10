package com.example.bakingapp.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.Fragments.RecipeInfoFragment;
import com.example.bakingapp.R;

public class RecipeInfoActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);
    }
}
