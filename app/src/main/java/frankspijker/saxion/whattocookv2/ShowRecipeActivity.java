package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ShowRecipeActivity extends AppCompatActivity {

    public static final String NAMEKEY = "namekey";
    public static final String DESCRIPTIONKEY =  "descriptionkey";
    public static final String IDKEY = "idkey";
    private TextView nameTextView;
    private TextView descriptionTextView;
    private RecyclerView showRecipeRecyclerview;
    private IngredientListAdapter showRecipeAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Ingredient> ingredientList;
    private Recipes recipeToShow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);

        InformationView informationView = findViewById(R.id.recipeInformation);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        int recipeId = intent.getIntExtra(IDKEY, 0);
        recipeToShow = Recipes.getRecipeById(recipeId);
//        Log.e("RecipeId", recipeId+"");
        Log.e("recipeinfo" , recipeToShow.toString());
        String title = recipeToShow.getTitle();
        getSupportActionBar().setTitle(title);
        informationView.setRecipeInformation(title, recipeToShow.getDescription());
        ingredientList = recipeToShow.getIngredientList();

        showRecipeRecyclerview = findViewById(R.id.ShowRecipeRecyclerview);
        showRecipeRecyclerview.hasFixedSize();
        showRecipeAdapter = new IngredientListAdapter(ingredientList, this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        showRecipeRecyclerview.setLayoutManager(layoutManager);
        showRecipeRecyclerview.setItemAnimator(new DefaultItemAnimator());
        showRecipeRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        showRecipeRecyclerview.setAdapter(showRecipeAdapter);
    }

    public void addIngredientToRecipe(View v) {
        int id = recipeToShow.getId();
        Intent intent = new Intent(this, AddIngredientActivity.class);
        intent.setFlags(id);
        intent.putExtra(AddIngredientActivity.IDKEY, id);
        startActivity(intent);
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof IngredientListAdapter.MyViewHolder) {
            //get name
            String name = ingredientList.get(viewHolder.getAdapterPosition()).getName();

            // create backup for undo
            final Ingredient deletedIngredient = ingredientList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove ingredient
            showRecipeAdapter.removeIngredient(viewHolder.getAdapterPosition());

            // show snackbar with undo
            Snackbar snackbar = Snackbar.make(findViewById(R.id.my_recycler_view), name + " deleted.", Snackbar.LENGTH_LONG);
            snackbar.setAction(getString(R.string.undo), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showRecipeAdapter.restoreIngredient(deletedIngredient, deletedIndex);
                }
            });
            snackbar.setActionTextColor(getResources().getColor(R.color.delete_background));
            snackbar.show();
        }
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
