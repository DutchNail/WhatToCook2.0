package frankspijker.saxion.whattocookv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.Objects;

public class ShowIngredientActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView descriptionTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ingredient);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameTextView = findViewById(R.id.nameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();

//        getSupportActionBar().setTitle(intent.getStringExtra("name"));
        nameTextView.setText(intent.getStringExtra("name"));
        descriptionTextView.setText(intent.getStringExtra("description"));



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