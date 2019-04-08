package frankspijker.saxion.whattocookv2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class AddRecipeIngredientAdapter extends RecyclerView.Adapter<AddRecipeIngredientAdapter.MyViewHolder>  {
    private Context context;
    private List<Ingredient> ingredientList;

    public AddRecipeIngredientAdapter(List<Ingredient> ingredientList, Context context) {
        this.ingredientList = ingredientList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description;
        public Button addButton;

        public MyViewHolder(View view) {
            super(view);

        }
    }


    @Override
    public AddRecipeIngredientAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AddRecipeIngredientAdapter.MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
