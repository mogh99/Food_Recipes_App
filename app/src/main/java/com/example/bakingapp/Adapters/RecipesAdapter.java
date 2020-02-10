package com.example.bakingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.R;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    private int numberOfRecipes;

    private Context context;

    private List<Recipe> recipes;

    private final RecipesOnClickHandler clickHandler;

    public interface RecipesOnClickHandler{
        void onClick(Recipe recipe);
    }

    public RecipesAdapter(RecipesOnClickHandler clickHandler){
        this.clickHandler = clickHandler;
    }

    public void setRecipesName(List<Recipe> recipesName, int numberOfRecipes){
        this.numberOfRecipes = numberOfRecipes;
        this.recipes = recipesName;
        notifyDataSetChanged();
    }

    @Override
    public RecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutID = R.layout.recipes_adapter;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutID, parent, shouldAttachToParentImmediately);

        RecipesViewHolder viewHolder = new RecipesViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        String recipeName = recipes.get(position).getName();
        holder.recipeNameTV.setText(recipeName);
    }

    @Override
    public int getItemCount() {
        return numberOfRecipes;
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private TextView recipeNameTV;

        public RecipesViewHolder(View itemView){
            super(itemView);
            recipeNameTV = (TextView) itemView.findViewById(R.id.tv_element_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(recipes.get(adapterPosition));
        }
    }


}
