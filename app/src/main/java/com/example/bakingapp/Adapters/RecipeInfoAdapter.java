package com.example.bakingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.Data.Step;
import com.example.bakingapp.R;

public class RecipeInfoAdapter extends RecyclerView.Adapter<RecipeInfoAdapter.RecipeInfoViewHolder>{

    private int numberOfElements; // numberOfElements = numberOfSteps + 1(ingredient)

    private Context context;

    private Recipe recipe;

    private final RecipeInfoOnClickHandler clickHandler;

    public interface RecipeInfoOnClickHandler{
        void onClick(Step step);
    }

    public RecipeInfoAdapter(RecipeInfoOnClickHandler clickHandler){
        this.clickHandler = clickHandler;
    }

    public void setRecipeData(Recipe recipe){
        this.recipe = recipe;
        numberOfElements = recipe.getSteps().size();
    }

    @NonNull
    @Override
    public RecipeInfoAdapter.RecipeInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutID = R.layout.recipe_info_adapter;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutID, parent, shouldAttachToParentImmediately);

        RecipeInfoViewHolder viewHolder = new RecipeInfoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeInfoAdapter.RecipeInfoViewHolder holder, int position) {
        holder.elementTitle.setText(recipe.getSteps().get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return numberOfElements;
    }

    public class RecipeInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView elementTitle;

        public RecipeInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            elementTitle = (TextView) itemView.findViewById(R.id.tv_steps);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(recipe.getSteps().get(adapterPosition));
        }
    }

}
