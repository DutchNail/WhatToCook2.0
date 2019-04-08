package frankspijker.saxion.whattocookv2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class AddRecipeIngredientAdapter extends RecyclerView.Adapter<AddRecipeIngredientAdapter.MyViewHolder>  {
    private Context context;
    private List<Ingredient> ingredientList;
    private List<Ingredient> addedIngredients;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public EditText description;
        public Button addButton;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.addRecipeName);
//            description = view.findViewById(R.id.addRecipeDescription);
//            addButton = view.findViewById(R.id.addButton);
        }
    }

    public AddRecipeIngredientAdapter(List<Ingredient> ingredientList, Context context) {
        this.ingredientList = IngredientProvider.getIngredientList();
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_add_recipe, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Ingredient ingredient = ingredientList.get(position);
        holder.name.setText(ingredient.getName());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void addIngredient(int position) {
        addedIngredients.add(ingredientList.get(position));
        notifyItemInserted(position);
    }


}
