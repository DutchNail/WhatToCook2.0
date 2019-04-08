package frankspijker.saxion.whattocookv2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.MyViewHolder> {
    private Context context;
    private List<Recipes> recipesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }

    public RecipeListAdapter(Context context) {
        this.context = context;
        this.recipesList = RecipesProvider.getRecipesList();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_view, parent, false);
        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Recipes recipe = recipesList.get(position);
        holder.name.setText(recipe.getTitle());
        holder.description.setText(recipe.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ShowRecipeActivity.class);
                i.putExtra(ShowRecipeActivity.NAMEKEY, recipe.getTitle());
                i.putExtra(ShowRecipeActivity.DESCRIPTIONKEY, recipe.getDescription());
                view.getContext().startActivity(i);
            }
        });
    }


    public int getItemCount() { return recipesList.size(); }

    public void removeRecipe(int position) {
        recipesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreRecipe(Recipes recipe, int position) {
        recipesList.add(position, recipe);
        notifyItemInserted(position);
    }
}
