package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DisplayRecipesActivity extends AppCompatActivity implements RecyclerRecipeTouchHelper.RecyclerItemTouchHelperListener, NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecipeListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Recipes> recipesList;
    public FloatingActionButton fab;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipes);

        Intent intent = getIntent();

        setToolbar();
        setNavigation();
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        recipesList = new ArrayList<>();
        getRecipes();
        recipesList = RecipesProvider.getRecipesList();
        mAdapter = new RecipeListAdapter(this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerRecipeTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

        // voortgangsbalkje met ingredienten afvinken?
    }

    private void getRecipes() {
        if(RecipesProvider.getSize() == 0) {
            List<Ingredient> ingredientList = new ArrayList<>();
            Ingredient i = new Ingredient("Water", 50, Ingredient.AmountType.deciliter);
            ingredientList.add(i);
            Recipes r = new Recipes("pizza", "lekker", ingredientList);
            RecipesProvider.addItem(r);
        }
    }

    private void setNavigation() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddRecipeActivity.class);
                startActivity(i);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home/up button, so long as you specify a parent activity in androidmanifest.xml
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ingredients) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_recipes) {
            Intent i = new Intent(this, DisplayRecipesActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
            //get name
            String name = recipesList.get(viewHolder.getAdapterPosition()).getTitle();

            // create backup for undo
            final Recipes deletedRecipe = recipesList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove ingredient
            mAdapter.removeRecipe(viewHolder.getAdapterPosition());

            // show snackbar with undo
            Snackbar snackbar = Snackbar.make(findViewById(R.id.my_recycler_view), name + " deleted.", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapter.restoreRecipe(deletedRecipe, deletedIndex);
                }
            });
            snackbar.setActionTextColor(getResources().getColor(R.color.delete_background));
            snackbar.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
