package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.ColorRes;
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
//??
//import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements RecyclerIngredientTouchHelper.RecyclerItemTouchHelperListener, NavigationView.OnNavigationItemSelectedListener {

//    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
//    private RecyclerView.Adapter mAdapter;
    private IngredientListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Ingredient> ingredientList;
    public Toolbar toolbar;
    public FloatingActionButton fab;
    private List<Recipes> recepten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setNavigation();
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        ingredientList = new ArrayList<>();
        prepareIngredients();
        ingredientList = IngredientProvider.getIngredientList();
        mAdapter = new IngredientListAdapter(ingredientList, this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerIngredientTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        //making hhtp call and fetching ingredients json

    }

    /**
     * mathod make volley network call and parses json
     */
    private void prepareIngredients() {
        if(IngredientProvider.getSize() == 0) {
            Ingredient i = new Ingredient("Water", 50, "deciliter");
            Ingredient j = new Ingredient("Kipfilet", 300, "gram");
            IngredientProvider.addIngredient(i);
            IngredientProvider.addIngredient(j);
        }
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
    }

    private void setNavigation() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddIngredientActivity.class);
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

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof IngredientListAdapter.MyViewHolder) {
            //get name
            String name = ingredientList.get(viewHolder.getAdapterPosition()).getName();

            // create backup for undo
            final Ingredient deletedIngredient = ingredientList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove ingredient
            mAdapter.removeIngredient(viewHolder.getAdapterPosition());

            // show snackbar with undo
            Snackbar snackbar = Snackbar.make(findViewById(R.id.my_recycler_view), name + " deleted.", Snackbar.LENGTH_LONG);
            snackbar.setAction(getString(R.string.undo), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapter.restoreIngredient(deletedIngredient, deletedIndex);
                }
            });
            snackbar.setActionTextColor(getResources().getColor(R.color.delete_background));
            snackbar.show();
        }
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

    @Override
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ingredients) {
            // Handle the camera action
        } else if (id == R.id.nav_recipes) {
            Intent i = new Intent(this, DisplayRecipesActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
