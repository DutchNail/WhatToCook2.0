package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {
    private TextView nameTextview;
    private TextView descriptionTextview;
    private EditText nameEdittext;
    private EditText descriptionEdittext;
    private RecyclerView addIngredients;
    private AddRecipeIngredientAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public List<Ingredient> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        nameTextview = findViewById(R.id.recipeNameTextview);
        nameEdittext = findViewById(R.id.recipeNameEdittext);
        descriptionTextview = findViewById(R.id.recipeDescriptionTextview);
        descriptionEdittext = findViewById(R.id.recipeDescriptionEdittext);

        addIngredients = findViewById(R.id.addRecipeRecyclerview);
        addIngredients.setHasFixedSize(true);

        ingredientList = new ArrayList<>();
        Ingredient i = new Ingredient("test",1, Ingredient.AmountType.centiliter);
//        IngredientProvider.addIngredient(i);
//        ingredientList = IngredientProvider.getIngredientList();
        ingredientList.add(i);
        mAdapter = new AddRecipeIngredientAdapter(ingredientList, this);
        layoutManager = new LinearLayoutManager(this);
        addIngredients.setLayoutManager(layoutManager);
        addIngredients.setItemAnimator(new DefaultItemAnimator());
        addIngredients.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        addIngredients.setAdapter(mAdapter);
    }

    public void saveRecipe(View v) {
        String name = nameEdittext.getText().toString();
        String description = descriptionEdittext.getText().toString();
        Ingredient i = new Ingredient("test", 2, Ingredient.AmountType.gram);
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(i);
        Recipes r = new Recipes(name, description, ingredientList);
        RecipesProvider.addItem(r);
        Intent intent = new Intent(this, DisplayRecipesActivity.class);
        startActivity(intent);
    }

    public void addIngredient(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof AddRecipeIngredientAdapter.MyViewHolder) {
            String name = ingredientList.get(viewHolder.getAdapterPosition()).getName();
            mAdapter.addIngredient(position);
            Snackbar snackbar = Snackbar.make(findViewById(R.id.addRecipeRecyclerview), name + " deleted at position" + position, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
