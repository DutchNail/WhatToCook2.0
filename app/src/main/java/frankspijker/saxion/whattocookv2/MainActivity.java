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


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private IngredientListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Ingredient> ingredientList;
    public Toolbar toolbar;
//    public FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setNavigation();
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        ingredientList = new ArrayList<>();
        prepareData();
        ingredientList = IngredientProvider.getIngredientList();
        mAdapter = new IngredientListAdapter(ingredientList, this, false);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * mathod make volley network call and parses json
     */
    private void prepareData() {
        if(RecipesProvider.getSize() == 0) {
            List<Ingredient> empty = new ArrayList<>();
            Recipes test = new Recipes("test", "test", empty);
            RecipesProvider.addItem(test);
            List<Ingredient> bamiIngredienten = new ArrayList<>();
            bamiIngredienten.add(new Ingredient("Mienestjes", 250, "gram"));
            bamiIngredienten.add(new Ingredient("Knoflook", 1, "pieces"));
            bamiIngredienten.add(new Ingredient("Bamigroenten", 900, "gram"));
            bamiIngredienten.add(new Ingredient("Eieren", 4, "pieces"));
            Recipes Bami = new Recipes("Bami", "Snelle bami voor 4 personen.", bamiIngredienten);
            List<Ingredient> ceasarIngredienten = new ArrayList<>();
            ceasarIngredienten.add(new Ingredient("Grana Padona Kazen", 100, "gram"));
            ceasarIngredienten.add(new Ingredient("Stokbrood", 1, "pieces"));
            ceasarIngredienten.add(new Ingredient("Kropjes Babyromainesla", 900, "gram"));
            ceasarIngredienten.add(new Ingredient("Eieren", 4, "pieces"));
            ceasarIngredienten.add(new Ingredient("Ansjovisfilets in olie in blik", 130, "gram"));
            Recipes ceasarSalade = new Recipes("Ceasar Salade", "Eenvoudig, maar oh zo lekker, niet voor niks een klassieker.", ceasarIngredienten);
            List<Ingredient> druiventaartIngredienten = new ArrayList<>();
            druiventaartIngredienten.add(new Ingredient("Monchou", 200, "gram"));
            druiventaartIngredienten.add(new Ingredient("Witte basterdsuiker", 100, "gram"));
            druiventaartIngredienten.add(new Ingredient("kant-en-klare taartbodem", 200, "gram"));
            druiventaartIngredienten.add(new Ingredient("pitloze druiven", 500, "gram"));
            Recipes druivenTaart = new Recipes("Druiventaart", "Een lekker en gezond nagerecht", druiventaartIngredienten);
            RecipesProvider.addItem(Bami);
            RecipesProvider.addItem(ceasarSalade);
            RecipesProvider.addItem(druivenTaart);
        }
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
    }

    private void setNavigation() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ingredients) {
            //Do nothing, current page.
        } else if (id == R.id.nav_recipes) {
            Intent i = new Intent(this, DisplayRecipesActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
