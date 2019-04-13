package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private IngredientListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public List<Ingredient> ingredientList;
    public Recipes recipe;
    public String recipeName;
    public String recipeDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: ProgressBar die bijhoudt welke velden al zijn ingevuld.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        nameTextview = findViewById(R.id.recipeNameTextview);
        nameEdittext = findViewById(R.id.recipeNameEdittext);
        descriptionTextview = findViewById(R.id.recipeDescriptionTextview);
        descriptionEdittext = findViewById(R.id.recipeDescriptionEdittext);
    }

    public void saveRecipe(View v) {
        recipeName = nameEdittext.getText().toString();
        recipeDescription = descriptionEdittext.getText().toString();
        ingredientList = new ArrayList<>();
        recipe = new Recipes(recipeName, recipeDescription, ingredientList);
        RecipesProvider.addItem(recipe);
        Intent intent = new Intent(this, DisplayRecipesActivity.class);
        startActivity(intent);
    }
}
