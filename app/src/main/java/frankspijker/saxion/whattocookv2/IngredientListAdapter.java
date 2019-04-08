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

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.MyViewHolder> {
    private Context context;
    private List<Ingredient> ingredientList;
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

    public IngredientListAdapter(List<Ingredient> ingredientList, Context context) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Ingredient ingredient = ingredientList.get(position);
        holder.name.setText(ingredient.getName());
        holder.description.setText(ingredient.getAmount() + " " + ingredient.getAmountType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ShowIngredientActivity.class);
                i.putExtra("name", ingredient.getName());
                i.putExtra("description", ingredient.getAmount() + " " + ingredient.getAmountType());

                view.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void removeIngredient(int position) {
        ingredientList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreIngredient(Ingredient ingredient, int position) {
        ingredientList.add(position, ingredient);
        notifyItemInserted(position);
    }



}
