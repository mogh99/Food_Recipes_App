package com.example.bakingapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Activites.IngredientActivity;
import com.example.bakingapp.Activites.StepActivity;
import com.example.bakingapp.Adapters.RecipeInfoAdapter;
import com.example.bakingapp.Data.Ingredient;
import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.Data.Step;
import com.example.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeInfoFragment extends Fragment implements RecipeInfoAdapter.RecipeInfoOnClickHandler, View.OnClickListener {

    private RecyclerView recipesInfoRecyclerView;
    private RecipeInfoAdapter recipeInfoAdapter;

    private TextView ingredientsLabel;

    private Recipe recipe;

    public RecipeInfoFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_info_fragment, container, false);

        recipe = (Recipe) getActivity().getIntent().getSerializableExtra(String.valueOf(R.string.recipeKey));

        ingredientsLabel = (TextView) rootView.findViewById(R.id.tv_ingredients);
        ingredientsLabel.setOnClickListener(this);

        recipesInfoRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recipesInfoRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recipesInfoRecyclerView.setLayoutManager(linearLayoutManager);

        //pass the recipe data through the constructor
        recipeInfoAdapter = new RecipeInfoAdapter(this);
        recipeInfoAdapter.setRecipeData(recipe);

        recipesInfoRecyclerView.setAdapter(recipeInfoAdapter);

        return rootView;
    }

    @Override
    public void onClick(Step step) {
        Context context = getContext();
        Class destinationClass = StepActivity.class;

        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(String.valueOf(R.string.stepsKey), step);

        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view == ingredientsLabel){
            Context context = getContext();
            Class destinationClass = IngredientActivity.class;

            ArrayList<Ingredient> ingredientsList = (ArrayList<Ingredient>) recipe.getIngredients();

            Intent intent = new Intent(context, destinationClass);
            intent.putExtra(String.valueOf(R.string.ingredientsKey), ingredientsList);

            startActivity(intent);
        }
        else
            return;
    }
}
