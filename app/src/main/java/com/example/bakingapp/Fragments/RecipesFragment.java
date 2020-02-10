package com.example.bakingapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Activites.RecipeInfoActivity;
import com.example.bakingapp.Adapters.RecipesAdapter;
import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.NetworkUtils.HTTPRequest;
import com.example.bakingapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesFragment extends Fragment implements RecipesAdapter.RecipesOnClickHandler{

    private RecyclerView recipesRecyclerView;
    private RecipesAdapter recipesAdapter;

    private ProgressBar loadingProgressBar;
    private TextView errorMessage;

    public RecipesFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipes_fragment, container, false);

        loadingProgressBar = (ProgressBar) rootView.findViewById(R.id.loading_progress_bar);
        errorMessage = (TextView) rootView.findViewById(R.id.error_message);


        recipesRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recipesRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesAdapter = new RecipesAdapter(this);
        loadData();

        recipesRecyclerView.setAdapter(recipesAdapter);

        return rootView;
    }

    private void loadData(){
        showProgressBar();

        Call<List<Recipe>> callRecipes  = HTTPRequest.getResponse().getRecipes();

        callRecipes.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipes = response.body();
                int numberOfRecipes = response.body().size();

                recipesAdapter.setRecipesName(recipes, numberOfRecipes);
                hideProgressBar();
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    @Override
    public void onClick(Recipe recipe) {
        Context context = getContext();
        Class destinationClass = RecipeInfoActivity.class;

        Intent intent = new Intent(context, destinationClass);

        intent.putExtra(String.valueOf(R.string.recipeKey), recipe);

        startActivity(intent);
    }

    public void showProgressBar(){
        loadingProgressBar.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.GONE);
        recipesRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgressBar(){
        recipesRecyclerView.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.GONE);
    }

    public void showErrorMessage(){
        errorMessage.setVisibility(View.VISIBLE);
        recipesRecyclerView.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.GONE);
    }
}
