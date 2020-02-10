package com.example.bakingapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Adapters.IngredientAdapter;
import com.example.bakingapp.Data.Ingredient;
import com.example.bakingapp.R;

import java.util.ArrayList;

public class IngredientFragment extends Fragment{

    private RecyclerView ingredientsRecyclerView;
    private IngredientAdapter ingredientAdapter;

    private ArrayList<Ingredient> ingredients;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredient_fragment, container, false);

        ingredients = (ArrayList<Ingredient>) getActivity().getIntent().getSerializableExtra(String.valueOf(R.string.ingredientsKey));

        ingredientsRecyclerView = (RecyclerView) rootView.findViewById(R.id.ingredients_recycler_view);
        ingredientsRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linerLinearLayoutManager = new LinearLayoutManager(getContext());
        ingredientsRecyclerView.setLayoutManager(linerLinearLayoutManager);

        ingredientAdapter = new IngredientAdapter(ingredients.size());
        ingredientAdapter.setIngredients(ingredients);

        ingredientsRecyclerView.setAdapter(ingredientAdapter);

        return rootView;
    }
}
