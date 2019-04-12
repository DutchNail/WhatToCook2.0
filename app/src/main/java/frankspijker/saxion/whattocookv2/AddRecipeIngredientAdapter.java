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

public class AddRecipeIngredientAdapter extends RecyclerView.Adapter<AddRecipeIngredientHolder>  {
    private Context context;
    private List<Ingredient> ingredientList;
    private List<Ingredient> addedIngredients;
    View.OnClickListener clickListener;
    OnAddButtonItemClickListener addButtonListener;

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

    public void setOnItemClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setAddButtonListener(OnAddButtonItemClickListener addButtonListener) {
        this.addButtonListener = addButtonListener;
    }


    @Override
    public AddRecipeIngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_add_recipe, parent, false);
        return new AddRecipeIngredientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AddRecipeIngredientHolder holder, final int position) {
        if(clickListener != null) {
            holder.itemView.setOnClickListener(clickListener);
        }

        if(addButtonListener != null) {
            AddRecipeIngredientHolder myHolder = (AddRecipeIngredientHolder) holder;
            myHolder.getAddButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addButtonListener.onAddIsClick(v, position);
                }
            });
        }

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

    public Ingredient getIngredient(int position) {
        Ingredient ingredient = ingredientList.get(position);
        return ingredient;
    }


}
