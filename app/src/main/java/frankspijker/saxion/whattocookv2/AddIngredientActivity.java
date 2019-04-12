package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AddIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: Add Ingredient fixen.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);
        Intent intent = getIntent();
        if(intent.hasExtra("id")) {
            int recipeId = (int) intent.getExtras().get("id");
            Log.v("aaa", recipeId + "");
            System.out.println(recipeId);
        }
    }
}
