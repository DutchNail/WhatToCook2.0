package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import static frankspijker.saxion.whattocookv2.Recipes.getRecipeById;

public class AddIngredientActivity extends AppCompatActivity {
    public Ingredient.AmountType amountType;
    public static final String IDKEY = "idkey";
    public static final String EDITKEY = "editkey";
    public Spinner spinner;
    public Ingredient ingredientToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: ProgressBar die bijhoudt welke velden al zijn ingevuld.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);
        EditText ingredientName = (EditText) findViewById(R.id.ingredientNameEdittext);
        EditText ingredientAmount = (EditText) findViewById(R.id.ingredientAmountEdittext);
        Intent intent = getIntent();
        if(intent.hasExtra(EDITKEY)) {
            ingredientToEdit = Ingredient.getIngredientById(intent.getFlags());
            Log.e("ingredientInfo", ingredientToEdit.toString());
            String name = ingredientToEdit.getName();
            double amount = ingredientToEdit.getAmount();
            ingredientName.setText(name);
            ingredientAmount.setText(amount+"");
        }
        spinner = (Spinner) findViewById(R.id.spinner);
        SpinnerActivity spinnerActivity = new SpinnerActivity();
        spinner.setOnItemSelectedListener(spinnerActivity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.amount_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }



    public void saveIngredient(View view) {
        EditText ingredientName = (EditText) findViewById(R.id.ingredientNameEdittext);
        EditText ingredientAmount = (EditText) findViewById(R.id.ingredientAmountEdittext);
        String name = ingredientName.getText().toString();
        String value = ingredientAmount.getText().toString();
        double amount = Double.parseDouble(value);
        String amountType = spinner.getSelectedItem().toString();
        //TODO: Spinner default veranderen, checken of er een artikel gekozen is. (Optioneel)
        Ingredient i = new Ingredient(name, amount, amountType);
        Intent intent = getIntent();
        if(intent.hasExtra(IDKEY)) {
            Log.e("id", intent.getFlags() + "");
            final Recipes recipe = Recipes.getRecipeById(intent.getFlags());
            Log.e("Recipe info", recipe.toString());
            recipe.addIngredient(i);
            IngredientProvider.addIngredient(i);
            Intent backToRecipe = new Intent(this, ShowRecipeActivity.class);
            backToRecipe.putExtra(ShowRecipeActivity.IDKEY, intent.getFlags());
            startActivity(backToRecipe);
        } else if(intent.hasExtra(EDITKEY)) {
//            Ingredient ingredientToEdit = Ingredient.getIngredientById(intent.getFlags());
            ingredientToEdit.setName(name);
            ingredientToEdit.setAmount(amount);
            Intent backToOverview = new Intent(this, ShowIngredientActivity.class);
            backToOverview.setFlags(ingredientToEdit.getId());
            startActivity(backToOverview);
        } else {
            IngredientProvider.addIngredient(i);
            Intent backToOverview = new Intent(this, MainActivity.class);
            startActivity(backToOverview);
        }
    }

}
