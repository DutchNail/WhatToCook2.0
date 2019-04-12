package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddIngredientActivity extends AppCompatActivity {
    public Intent intent;
    public EditText ingredientName;
    public EditText ingredientAmount;
    public Ingredient.AmountType amountType;
    public Recipes recipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: Add Ingredient fixen.
        ingredientName = (EditText) findViewById(R.id.ingredientNameEdittext);
        ingredientAmount = (EditText) findViewById(R.id.ingredientAmountEdittext);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        SpinnerActivity spinnerActivity = new SpinnerActivity();
        spinner.setOnItemSelectedListener(spinnerActivity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.amount_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        intent = getIntent();
    }



    public void saveIngredient(View view) {
        //TODO: Crash't bij opslaan,
        // zowel vanuit mainactivity(Ingredienten overview
        // dus zonder dat ie hem toevoegd aan recept
        // als wanneer je hem vanuit het recept wil toevoegen
        //idee: Vanuit recept moet hij hem ook toevoegen aan de Recipes.ingredientList
        // vanuit ingredientOverview(MainActivity) gewoon opslaan.

        String name = ingredientName.getText().toString();
        String value = ingredientAmount.getText().toString();
        int amount = Integer.parseInt(value);

        Ingredient i = new Ingredient(name, amount, Ingredient.AmountType.centiliter);
        if(intent.hasExtra("id")) {
            int recipeId = (int) intent.getExtras().get("id");
            recipe = Recipes.getRecipeById(recipeId);
            recipe.addIngredient(i);
            IngredientProvider.addIngredient(i);
            Intent backToRecipe = new Intent(this, AddRecipeActivity.class);
            backToRecipe.putExtra("id", recipe.getId()+"");
            startActivity(backToRecipe);
        } else {
            IngredientProvider.addIngredient(i);
            Intent backToOverview = new Intent(this, ShowIngredientActivity.class);
            startActivity(backToOverview);
        }
    }

}
