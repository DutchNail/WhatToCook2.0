package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class ShowIngredientActivity extends AppCompatActivity {
    public static final String IDKEY = "ShowIngredientIdKey";
    public FloatingActionButton fab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ingredient);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        InformationView informationView = findViewById(R.id.ingredientInformation);
        Intent intent = getIntent();
        final Ingredient ingredient = Ingredient.getIngredientById(intent.getFlags());
        String ingredientName  = ingredient.getName();
//        String ingredientAmount  = ingredient.getAmount() + " " + ingredient.getType();
        Double amount = ingredient.getAmount();
        String amountType = ingredient.getType();
        informationView.setIngredientInformation(ingredientName, amount, amountType);
        getSupportActionBar().setTitle(ingredientName);
        Log.e("Ingredient Info", ingredient.toString());
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddIngredientActivity.class);
                intent.putExtra(AddIngredientActivity.EDITKEY, AddIngredientActivity.EDITKEY);
                intent.setFlags(ingredient.getId());
//                Log.e("ingredient ID", ingredient.getId()+"");
                startActivity(intent);
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ShowIngredientActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}