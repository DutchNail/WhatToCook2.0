package frankspijker.saxion.whattocookv2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddRecipeIngredientHolder extends RecyclerView.ViewHolder {
    private final Button addButton;
    public TextView name;
    public EditText description;

    public AddRecipeIngredientHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.addRecipeName);
        addButton = (Button) itemView.findViewById(R.id.addButton);
    }

    public Button getAddButton() {
        return addButton;
    }
}
