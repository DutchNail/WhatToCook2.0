package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ShowRecipeActivity extends AppCompatActivity {

    public static final String NAMEKEY = "namekey";
    public static final String DESCRIPTIONKEY =  "descriptionkey";
    public static final String INGREDIENTSKEY = "ingredientskey";
    private TextView nameTextView;
    private TextView descriptionTextView;
    private List<Ingredient> ingredientList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameTextView = findViewById(R.id.nameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();
        //intent meuk naar static finals

        getSupportActionBar().setTitle(intent.getStringExtra(NAMEKEY));

        nameTextView.setText(intent.getStringExtra(NAMEKEY));
        descriptionTextView.setText(intent.getStringExtra(DESCRIPTIONKEY));

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ShowRecipeActivity.this, DisplayRecipesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
