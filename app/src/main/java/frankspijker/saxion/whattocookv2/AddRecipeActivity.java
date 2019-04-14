package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    private CustomProgressBar addRecipeProgress;
    public List<Ingredient> ingredientList;
    public Recipes recipe;
    public String recipeName;
    public String recipeDescription;
    private boolean titleChanged = false;
    private boolean descriptionChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        addRecipeProgress = findViewById(R.id.addRecipeProgressbar);
        addRecipeProgress.setProgress(10);
        nameTextview = findViewById(R.id.recipeNameTextview);
        nameEdittext = findViewById(R.id.recipeNameEdittext);
        descriptionTextview = findViewById(R.id.recipeDescriptionTextview);
        descriptionEdittext = findViewById(R.id.recipeDescriptionEdittext);
        nameEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!titleChanged) {
                    addRecipeProgress.setProgress((int)addRecipeProgress.getProgress()+50);
                    titleChanged = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        descriptionEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!descriptionChanged) {
                    addRecipeProgress.setProgress((int)addRecipeProgress.getProgress()+50);
                    descriptionChanged = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
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
