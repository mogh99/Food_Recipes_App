package com.example.bakingapp.NetworkUtils;

import com.example.bakingapp.Data.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
}
