package frankspijker.saxion.whattocookv2;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

public class CustomProgressBar extends View {

    private Context context;
    private Paint progressColor = new Paint();
    private Paint bar = new Paint();
    private float progress = 0;

    public CustomProgressBar(Context context) {
        super(context);
        init(context);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        progressColor.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        bar.setColor(ContextCompat.getColor(context, R.color.delete_background));
    }

    public void setProgress(int i) {
        this.progress = i;
        invalidate();
    }

    public float getProgress() {
        return this.progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), bar);//draws the grey bar
        canvas.drawRect(0, 0, (float) (getWidth() / 100.0 * progress ), getHeight(), progressColor);//draws the progress bar
    }
}
