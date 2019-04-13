package frankspijker.saxion.whattocookv2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InformationView extends LinearLayout {

    private TextView nameLabel;
    private TextView descriptionLabel;
    private TextView nameTextview;
    private TextView descriptionTextview;

    public InformationView(Context context) {
        super(context);
        init();
    }

    public InformationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InformationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public InformationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.informationview, this);

        nameLabel = findViewById(R.id.textViewNameLabel);
        descriptionLabel = findViewById(R.id.descriptionTextViewLabel);
        nameTextview = findViewById(R.id.nameTextView);
        descriptionTextview = findViewById(R.id.descriptionTextView);

    }

    public void setRecipeInformation(String name, String description) {
        this.nameTextview.setText(name);
        this.descriptionLabel.setText(R.string.recipe_description);
        this.descriptionTextview.setText(description);
    }

    public void setIngredientInformation(String name, double amount, String amountType) {
        this.nameTextview.setText(name);
        this.descriptionLabel.setText(R.string.ingredient_amount);
        this.descriptionTextview.setText(amount + " " + amountType);
    }

}
