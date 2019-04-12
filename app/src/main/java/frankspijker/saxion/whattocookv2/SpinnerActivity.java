package frankspijker.saxion.whattocookv2;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Ingredient.AmountType amountType = (Ingredient.AmountType) parent.getItemAtPosition(position);

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}
