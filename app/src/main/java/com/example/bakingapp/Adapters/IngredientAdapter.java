package com.example.bakingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Data.Ingredient;
import com.example.bakingapp.R;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private Context context;

    private ArrayList<Ingredient> ingredients;

    private int numberOfIngredients;

    public IngredientAdapter(int numberOfIngredients){
        this.numberOfIngredients = numberOfIngredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutID = R.layout.ingredient_adapter;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutID, parent, shouldAttachToParentImmediately);

        IngredientViewHolder viewHolder = new IngredientViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);

        String ingredientName = ingredient.getIngredient();
        double ingredientQuantity = ingredient.getQuantity();
        String ingredientMeasure = ingredient.getMeasure();

        holder.ingredientLabel.setText(ingredientName);
        holder.ingredientQuantity.append(ingredientQuantity+"");
        holder.ingredientMeasure.append(ingredientMeasure);
    }

    @Override
    public int getItemCount() {
        return numberOfIngredients;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder{

        private TextView ingredientLabel;
        private TextView ingredientQuantity;
        private TextView ingredientMeasure;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientLabel = (TextView) itemView.findViewById(R.id.tv_ingredient_label);
            ingredientQuantity = (TextView) itemView.findViewById(R.id.tv_measure);
            ingredientMeasure = (TextView) itemView.findViewById(R.id.tv_quantity);
        }
    }
}
